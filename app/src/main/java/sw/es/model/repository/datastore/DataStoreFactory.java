package sw.es.model.repository.datastore;

import sw.es.model.repository.criteria.LoadCriteria;
import sw.es.model.repository.criteria.StoreCriteria;

/**
 * Created by alberto on 19/10/15.
 */
public interface DataStoreFactory<Params> {
    DataStore get(LoadCriteria loadCriteria, Params params);
    DataStore get(StoreCriteria storeCriteria);
}
