package sw.es.model.database.core;

import com.j256.ormlite.dao.Dao;

/**
 * Created by alberto on 18/10/15.
 */
public class CreateOrUpdateHelper {

    /**
     * Helper for query if a Dao.CreateOrUpdateStatus was created or updated
     * @param status
     * @return
     */
    public static boolean isCreatedOrUpdated(Dao.CreateOrUpdateStatus status){
        if (status == null) {
            return false;
        }
        if (status.isCreated() || status.isUpdated()) {
            return true;
        }
        return false;
    }
}
