package sw.es.model.database.rxquery;

import java.util.concurrent.Callable;

import rx.Observable;
import sw.es.model.database.core.DaoManager;
import sw.es.model.database.model.Entity;
import sw.es.model.database.query.Save;
import sw.es.model.database.rxquery.abs.RxAbstractQuery;
import sw.es.domain.rx.ObservableCreator;


/**
 * Created by alberto on 18/10/15.
 */
public class RxSave<Result extends Entity> extends RxAbstractQuery<Boolean> {


    private Result result;


    public RxSave(Result result) {
        this.result = result;
    }


    @Override
    protected Observable<Boolean> query(DaoManager daoManager){
        return ObservableCreator.create(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Save<Result>save = new Save<>(result);
                return save.run();
            }
        });
    }
}
