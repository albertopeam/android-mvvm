package sw.es.model.repository.exception;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public class NoMoreCriteriaException extends Exception {

    public NoMoreCriteriaException() {
        super("No more criteria found. Data cannot recovered from any source");
    }

}
