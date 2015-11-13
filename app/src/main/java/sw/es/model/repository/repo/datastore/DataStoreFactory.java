package sw.es.model.repository.repo.datastore;

import sw.es.model.repository.repo.criteria.LoadCriteria;
import sw.es.model.repository.repo.criteria.RemoveCriteria;
import sw.es.model.repository.repo.criteria.StoreCriteria;
import sw.es.model.repository.repo.exception.CriteriaExpiredException;

/**
 * Created by alberto on 19/10/15.
 */
public interface DataStoreFactory<Params> {
    DataStore get(LoadCriteria loadCriteria, Params params) throws CriteriaExpiredException;
    DataStore get(StoreCriteria storeCriteria);
    DataStore get(RemoveCriteria removeCriteria);
}
