package sw.es.domain.repository.repo.exception;

/**
 * Created by albertopenasamor on 28/5/15.
 */
public class NotStoredInRepositoryException extends Exception {

    public NotStoredInRepositoryException(Object object) {
        super(String.format("The object: %s couldn't be saved on the database. Review DataStore, maybe any SQLException.", object.getClass().getSimpleName()));
    }
}
