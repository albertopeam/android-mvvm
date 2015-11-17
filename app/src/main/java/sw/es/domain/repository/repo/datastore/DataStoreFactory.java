package sw.es.domain.repository.repo.datastore;

import sw.es.domain.repository.repo.criteria.LoadCriteria;
import sw.es.domain.repository.repo.criteria.RemoveCriteria;
import sw.es.domain.repository.repo.criteria.StoreCriteria;
import sw.es.domain.repository.repo.exception.CriteriaExpiredException;

/**
 * Created by alberto on 19/10/15.
 */
public interface DataStoreFactory<Params> {
    DataStore get(LoadCriteria loadCriteria, Params params) throws CriteriaExpiredException;
    DataStore get(StoreCriteria storeCriteria);
    DataStore get(RemoveCriteria removeCriteria);
}
