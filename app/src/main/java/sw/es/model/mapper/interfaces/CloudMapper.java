package sw.es.model.mapper.interfaces;

/**
 * Created by albertopenasamor on 15/10/15.
 */
public interface CloudMapper<Server,Local> {
    Local map(Server server);
}
