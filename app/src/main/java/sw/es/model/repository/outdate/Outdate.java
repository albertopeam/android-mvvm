package sw.es.model.repository.outdate;

/**
 * Created by Alberto Penas on 19/10/15.
 */
public interface Outdate<Model> {
    boolean isExpired();
    void setLastUpdate(Model model);
}
