package sw.es.model.database.query.abs;

/**
 * Created by alberto on 12/10/15.
 */
public interface Query<Result> {
    Result run() throws Exception;
}
