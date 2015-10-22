package sw.es.model.database.exception;

/**
 * Created by alberto on 29/9/15.
 */
public class DatabaseNotInitializedException extends RuntimeException {
    public DatabaseNotInitializedException(String whose) {
        super(whose + " isnt configured, you must config it before access");
    }
}
