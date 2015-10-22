package sw.es.model.repository.exception;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public abstract class NotFoundInDataStoreException extends Exception {

    public NotFoundInDataStoreException(Class clazz) {
        super(String.format("Not found in %s", clazz.getSimpleName()));
    }

}
