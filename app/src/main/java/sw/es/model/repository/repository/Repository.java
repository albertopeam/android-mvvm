package sw.es.model.repository.repository;


import rx.Observable;
import sw.es.model.repository.criteria.LoadCriteria;
import sw.es.model.repository.criteria.StoreCriteria;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public interface Repository<Model, Params> {
    void fetch(final Params params,final LoadCriteria loadCriteria, final FetchCallback<Model, Params> callback);
    void commit(Model model, StoreCriteria storeCriteria, final CommitCallback<Model, Params>callback);

    Observable<Model> pull(Params params, LoadCriteria loadCriteria);
    Observable<Boolean> push(Model model, StoreCriteria storeCriteria);
}
