package sw.es.model.repository.repository;


import rx.Observable;
import sw.es.model.repository.callback.FetchCallback;
import sw.es.model.repository.criteria.FetchCriteria;
import sw.es.model.repository.criteria.StoreCriteria;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public interface Repository<Model, Params> {
    void fetch(final Params params,final FetchCriteria fetchCriteria, final FetchCallback<Model, Params> callback);
    Observable<Model> pull(Params params, FetchCriteria fetchCriteria);
    Observable<Boolean> commit(Model model, StoreCriteria storeCriteria);
    Observable<Boolean> push(Model model, StoreCriteria storeCriteria);
}
