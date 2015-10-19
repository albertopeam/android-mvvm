package sw.es.repository.interfaces;

import sw.es.repository.criteria.FetchCriteria;
import sw.es.repository.criteria.StoreCriteria;

/**
 * Created by alberto on 19/10/15.
 */
public interface DataStoreFactory {
    DataStore get(FetchCriteria fetchCriteria);
    DataStore get(StoreCriteria storeCriteria);
}
