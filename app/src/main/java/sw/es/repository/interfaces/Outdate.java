package sw.es.repository.interfaces;

/**
 * Created by Alberto Penas on 19/10/15.
 */
public interface Outdate<T> {

    boolean isExpired(T t);

    void setLastUpdate(T t);

}
