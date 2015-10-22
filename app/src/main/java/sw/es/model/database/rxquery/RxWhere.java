package sw.es.model.database.rxquery;

import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import sw.es.model.database.core.DaoManager;
import sw.es.model.database.query.Where;
import sw.es.model.database.rxquery.abs.RxAbstractQuery;
import sw.es.model.rx.ObservableCreator;

/**
 * Created by alberto on 18/10/15.
 */
public class RxWhere<Result> extends RxAbstractQuery<List<Result>> {


    private Class aClass;
    private String columnName;
    private Object value;


    public RxWhere(Class aClass, String columnName, Object value) {
        this.aClass = aClass;
        this.columnName = columnName;
        this.value = value;
    }


    @Override
    protected Observable<List<Result>> query(DaoManager daoManager){
        return ObservableCreator.create(new Callable<List<Result>>() {
            @Override
            public List<Result> call() throws Exception {
                Where<Result>where = new Where<>(aClass, columnName, value);
                return where.run();
            }
        });
    }
}
