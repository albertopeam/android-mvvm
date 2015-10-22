package sw.es.model.repository.datastore;

import sw.es.model.repository.criteria.LoadCriteria;
import sw.es.model.repository.criteria.StoreCriteria;
import sw.es.model.repository.exception.NoMoreCriteriaException;
import sw.es.model.repository.outdate.Outdate;

/**
 * Created by alberto on 19/10/15.
 */
public abstract class AbsDataStoreFactory<Model> implements DataStoreFactory {


    private CloudDataStore<Model>cloudDataStore;
    private DBDataStore<Model>dbDataStore;
    private Outdate<Model> outdate;


    public AbsDataStoreFactory(CloudDataStore<Model> cloudDataStore, DBDataStore<Model> dbDataStore, Outdate<Model>outdate) {
        this.cloudDataStore = cloudDataStore;
        this.dbDataStore = dbDataStore;
        this.outdate = outdate;
    }

    @Override
    public DataStore get(LoadCriteria loadCriteria) {
        if (loadCriteria.getFetchCriteria().name().equals(LoadCriteria.FetchCriteriaEnum.GET.name())) {
            if (outdate.isExpired()) {
                try {
                    loadCriteria.next();
                } catch (NoMoreCriteriaException e) {
                    e.printStackTrace();
                    return dbDataStore;
                }
                return cloudDataStore;
            }
            return dbDataStore;
        } else if (loadCriteria.getFetchCriteria().name().equals(LoadCriteria.FetchCriteriaEnum.REFRESH.name())) {
            return cloudDataStore;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public DataStore get(StoreCriteria storeCriteria) {
        if (storeCriteria.name().equals(StoreCriteria.SAVE.name())) {
            return cloudDataStore;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
