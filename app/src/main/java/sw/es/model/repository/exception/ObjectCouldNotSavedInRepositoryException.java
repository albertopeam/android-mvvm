package sw.es.model.repository.exception;

/**
 * Created by albertopenasamor on 28/5/15.
 */
public class ObjectCouldNotSavedInRepositoryException extends Exception {
    public ObjectCouldNotSavedInRepositoryException() {
        super("El objecto no pudo ser guardado en el repositorio");
    }
}
