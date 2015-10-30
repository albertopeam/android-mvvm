package sw.es.model.repository.repository;

import sw.es.model.repository.criteria.RemoveCriteria;

/**
 * Created by albertopenasamor on 20/10/15.
 */
public interface RemoveCallback {
    void onRemove(RemoveCriteria removeCriteria, Boolean result);
    void onRemoveError(RemoveCriteria removeCriteria, Throwable throwable);
}
