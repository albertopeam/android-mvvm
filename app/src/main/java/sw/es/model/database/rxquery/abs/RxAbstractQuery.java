package sw.es.model.database.rxquery.abs;

import rx.Observable;
import sw.es.model.database.core.DaoManager;

/**
 * Created by alberto on 21/10/15.
 */
public abstract class RxAbstractQuery<Result> implements RxQuery<Result> {

    @Override
    public Observable<Result> run() {
        return query(new DaoManager());
    }

    protected abstract Observable<Result> query(DaoManager daoManager);
}
