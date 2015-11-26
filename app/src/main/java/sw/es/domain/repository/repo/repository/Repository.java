package sw.es.domain.repository.repo.repository;


import rx.Subscription;
import sw.es.domain.repository.repo.criteria.LoadCriteria;
import sw.es.domain.repository.repo.criteria.RemoveCriteria;
import sw.es.domain.repository.repo.criteria.StoreCriteria;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public interface Repository<Model, Params> {
    Subscription fetch(final Params params,final LoadCriteria loadCriteria, final LoadCallback<Model, Params> callback);
    Subscription commit(final Model model, final StoreCriteria storeCriteria, final CommitCallback callback);
    Subscription remove(final Params params, final RemoveCriteria removeCriteria, final RemoveCallback callback);
}
