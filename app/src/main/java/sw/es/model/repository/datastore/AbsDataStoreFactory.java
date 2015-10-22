package sw.es.model.repository.datastore;

import sw.es.model.repository.criteria.LoadCriteria;
import sw.es.model.repository.criteria.StoreCriteria;
import sw.es.model.repository.exception.NoMoreCriteriaException;
import sw.es.model.repository.outdate.Outdate;

/**
 * Created by alberto on 19/10/15.
 */
public abstract class AbsDataStoreFactory implements DataStoreFactory {


    private CloudDataStore cloudDataStore;
    private DBDataStore dbDataStore;
    private Outdate outdate;


    public AbsDataStoreFactory(CloudDataStore cloudDataStore, DBDataStore dbDataStore, Outdate outdate) {
        this.cloudDataStore = cloudDataStore;
        this.dbDataStore = dbDataStore;
        this.outdate = outdate;
    }

    @Override
    public DataStore get(LoadCriteria loadCriteria) {
        if (loadCriteria.isGet()) {
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
        } else if (loadCriteria.isRefresh()) {
            return cloudDataStore;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public DataStore get(StoreCriteria storeCriteria) {
        if (storeCriteria.isCommit()) {
            return cloudDataStore;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
