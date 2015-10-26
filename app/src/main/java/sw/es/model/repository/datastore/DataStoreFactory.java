package sw.es.model.repository.datastore;

import sw.es.model.repository.criteria.LoadCriteria;
import sw.es.model.repository.criteria.StoreCriteria;
import sw.es.model.repository.exception.CriteriaExpiredException;

/**
 * Created by alberto on 19/10/15.
 */
public interface DataStoreFactory<Params> {
    DataStore get(LoadCriteria loadCriteria, Params params) throws CriteriaExpiredException;
    DataStore get(StoreCriteria storeCriteria);
}
