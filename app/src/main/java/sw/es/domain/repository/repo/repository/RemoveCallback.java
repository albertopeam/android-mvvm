package sw.es.domain.repository.repo.repository;

import sw.es.domain.repository.repo.criteria.RemoveCriteria;

/**
 * Created by albertopenasamor on 20/10/15.
 */
public interface RemoveCallback {
    void onRemove(RemoveCriteria removeCriteria, Boolean result);
    void onRemoveError(RemoveCriteria removeCriteria, Throwable throwable);
}
