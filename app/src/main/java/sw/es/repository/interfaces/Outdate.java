package sw.es.repository.interfaces;

/**
 * Created by Alberto Penas on 19/10/15.
 */
public interface Outdate<Model> {
    boolean isExpired();
    void setLastUpdate(Model model);
}
