package sw.es.model.repository.repository;

import sw.es.model.repository.criteria.LoadCriteria;

/**
 * Created by albertopenasamor on 20/10/15.
 */
public interface LoadCallback<Model, Params> {
    void onFetch(Params params, LoadCriteria loadCriteria, Model model);
    void onFetchError(Params params, LoadCriteria loadCriteria, Throwable throwable);
}
