package sw.es.model.database.rxquery.abs;

import rx.Observable;

/**
 * Created by alberto on 21/10/15.
 */
public interface RxQuery<Result> {
    Observable<Result> run();
}
