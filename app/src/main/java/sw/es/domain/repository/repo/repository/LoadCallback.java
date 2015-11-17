package sw.es.domain.repository.repo.repository;

import sw.es.domain.repository.repo.criteria.LoadCriteria;

/**
 * Created by albertopenasamor on 20/10/15.
 */
public interface LoadCallback<Model, Params> {
    void onFetch(Params params, LoadCriteria loadCriteria, Model model);
    void onFetchError(Params params, LoadCriteria loadCriteria, Throwable throwable);
}
