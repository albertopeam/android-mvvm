package sw.es.model.repository.datastore;

import sw.es.model.repository.criteria.LoadCriteria;
import sw.es.model.repository.criteria.StoreCriteria;
import sw.es.model.repository.exception.CriteriaExpiredException;
import sw.es.model.repository.outdate.Outdate;

/**
 * Created by alberto on 19/10/15.
 */
public class AbsDataStoreFactory<Model, Params> implements DataStoreFactory<Params> {


    private CloudDataStore<Model, Params> cloudDataStore;
    private DBDataStore<Model, Params> dbDataStore;
    private Outdate<Params, Model> outdate;


    public AbsDataStoreFactory(CloudDataStore<Model, Params> cloudDataStore, DBDataStore<Model, Params> dbDataStore, Outdate<Params, Model> outdate) {
        this.cloudDataStore = cloudDataStore;
        this.dbDataStore = dbDataStore;
        this.outdate = outdate;
    }

    @Override
    public DataStore get(LoadCriteria loadCriteria, Params params) throws CriteriaExpiredException{
        if (loadCriteria.isGet()) {
            if (outdate.isExpired(params)) {
                throw new CriteriaExpiredException(LoadCriteria.class);
            }
            return dbDataStore;
        } else if (loadCriteria.isRefresh()) {
            return cloudDataStore;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public DataStore get(StoreCriteria storeCriteria) {
        if (storeCriteria.isCommit()){
            return dbDataStore;
        }else if(storeCriteria.isPush()) {
            return cloudDataStore;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
