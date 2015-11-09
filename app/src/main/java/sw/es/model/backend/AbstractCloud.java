package sw.es.model.backend;

/**
 * Created by alberto on 9/11/15.
 */
public abstract class AbstractCloud {

    public int cod;

    public boolean isSuccess(){
        return cod == 200;
    }
}
