package sw.es.repository.exceptions;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public class ObjectNotFoundInCloudRepositoryException extends Exception {

    public ObjectNotFoundInCloudRepositoryException() {
        super("Object not found in cloud repository");
    }

}
