package sw.es.model.database.rxquery;

import java.util.concurrent.Callable;

import rx.Observable;
import sw.es.model.database.core.DaoManager;
import sw.es.model.database.model.Entity;
import sw.es.model.database.query.DeleteWhere;
import sw.es.model.database.rxquery.abs.RxAbstractQuery;
import sw.es.model.rx.ObservableCreator;

/**
 * Created by alberto on 18/10/15.
 */
public class RxDeleteWhere<Result extends Entity> extends RxAbstractQuery<Boolean> {


    private Class aClass;
    private String columnName;
    private Object value;


    public RxDeleteWhere(Class aClass, String columnName, Object value) {
        this.aClass = aClass;
        this.columnName = columnName;
        this.value = value;
    }


    @Override
    protected Observable<Boolean> query(DaoManager daoManager){
        return ObservableCreator.create(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                DeleteWhere<Result> deleteWhere = new DeleteWhere<>(aClass, columnName, value);
                return deleteWhere.run();
            }
        });
    }
}
