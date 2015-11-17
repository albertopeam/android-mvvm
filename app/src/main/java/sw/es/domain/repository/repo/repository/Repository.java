package sw.es.domain.repository.repo.repository;


import sw.es.domain.repository.repo.criteria.LoadCriteria;
import sw.es.domain.repository.repo.criteria.RemoveCriteria;
import sw.es.domain.repository.repo.criteria.StoreCriteria;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public interface Repository<Model, Params> {
    void fetch(final Params params,final LoadCriteria loadCriteria, final LoadCallback<Model, Params> callback);
    void commit(final Model model, final StoreCriteria storeCriteria, final CommitCallback callback);
    void remove(final Params params, final RemoveCriteria removeCriteria, final RemoveCallback callback);
}
