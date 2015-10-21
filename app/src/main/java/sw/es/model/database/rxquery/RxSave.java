package sw.es.model.database.rxquery;

import com.j256.ormlite.dao.Dao;

import java.util.concurrent.Callable;

import rx.Observable;
import sw.es.model.database.core.CreateOrUpdateHelper;
import sw.es.model.database.core.DaoManager;
import sw.es.model.database.model.Entity;
import sw.es.model.database.rxquery.abs.RxAbstractQuery;
import sw.es.model.rx.ObservableCreator;


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
        final Dao<Result,Integer> dao = daoManager.getDaoFor(result.getClass());
        return ObservableCreator.create(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return CreateOrUpdateHelper.isCreatedOrUpdated(dao.createOrUpdate(result));
            }
        });
    }
}
