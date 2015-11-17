package sw.es.domain.repository.repo.exception;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public class NotFoundInRepositoryException extends Exception {

    public NotFoundInRepositoryException(Class clazz) {
        super(String.format("Not found in %s", clazz.getSimpleName()));
    }

}
