package sw.es.model.repository.repository;

import sw.es.model.repository.criteria.StoreCriteria;

/**
 * Created by albertopenasamor on 20/10/15.
 */
public interface CommitCallback {
    void onCommit(StoreCriteria storeCriteria, Boolean result);
    void onCommitError(StoreCriteria storeCriteria, Throwable throwable);
}
