package sw.es.model.repository.outdate;

/**
 * Created by Alberto Penas on 19/10/15.
 */
public interface Outdate<Params, Model> {
    boolean isExpired(Params params);
    void setLastUpdate(Model model);
    void remove(Params params);
}
