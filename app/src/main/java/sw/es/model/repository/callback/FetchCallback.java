package sw.es.model.repository.callback;

import sw.es.model.repository.criteria.FetchCriteria;

/**
 * Created by albertopenasamor on 20/10/15.
 */
public interface FetchCallback<Model, Params> {
    void onFetch(Params params, FetchCriteria fetchCriteria, Model model);
    void onFetchError(Params params, FetchCriteria fetchCriteria, Throwable throwable);
}
