package sw.es.model.repository.repo.datastore;

import sw.es.model.repository.repo.criteria.LoadCriteria;
import sw.es.model.repository.repo.criteria.RemoveCriteria;
import sw.es.model.repository.repo.criteria.StoreCriteria;
import sw.es.model.repository.repo.exception.CriteriaExpiredException;
import sw.es.model.repository.repo.outdate.Outdate;

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
            throw new UnsupportedOperationException();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public DataStore get(RemoveCriteria removeCriteria) {
        if (removeCriteria.isCommit()){
            return dbDataStore;
        }else if(removeCriteria.isPush()) {
            throw new UnsupportedOperationException();
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
