package sw.es.model.repository.repository;


import sw.es.model.repository.criteria.LoadCriteria;
import sw.es.model.repository.criteria.RemoveCriteria;
import sw.es.model.repository.criteria.StoreCriteria;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public interface Repository<Model, Params> {
    void fetch(final Params params,final LoadCriteria loadCriteria, final LoadCallback<Model, Params> callback);
    void commit(final Model model, final StoreCriteria storeCriteria, final CommitCallback callback);
    void remove(final Params params, final RemoveCriteria removeCriteria, final RemoveCallback callback);
}
