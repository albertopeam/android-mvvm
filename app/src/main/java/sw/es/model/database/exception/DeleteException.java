package sw.es.model.database.exception;

/**
 * Created by albertopenasamor on 30/10/15.
 */
public class DeleteException extends Exception {

    public DeleteException(Class clazz, int delRows, int expected) {
        super(String.format("deleted %s from %s, when expected to delete %s", String.valueOf(delRows), clazz.getSimpleName(), String.valueOf(expected)));
    }
}
