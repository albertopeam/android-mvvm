package sw.es.model.repository.exception;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public class NotFoundInDBDataStoreException extends NotFoundInDataStoreException {

    public NotFoundInDBDataStoreException(Class clazz) {
        super(clazz);
    }

}
