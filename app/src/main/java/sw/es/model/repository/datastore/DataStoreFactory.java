package sw.es.model.repository.datastore;

import sw.es.model.repository.criteria.FetchCriteria;
import sw.es.model.repository.criteria.StoreCriteria;

/**
 * Created by alberto on 19/10/15.
 */
public interface DataStoreFactory {
    DataStore get(FetchCriteria fetchCriteria);
    DataStore get(StoreCriteria storeCriteria);
}
