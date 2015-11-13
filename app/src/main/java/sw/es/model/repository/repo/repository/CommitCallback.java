package sw.es.model.repository.repo.repository;

import sw.es.model.repository.repo.criteria.StoreCriteria;

/**
 * Created by albertopenasamor on 20/10/15.
 */
public interface CommitCallback {
    void onCommit(StoreCriteria storeCriteria, Boolean result);
    void onCommitError(StoreCriteria storeCriteria, Throwable throwable);
}
