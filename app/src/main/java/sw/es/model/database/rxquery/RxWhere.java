package sw.es.model.database.rxquery;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import sw.es.model.database.core.DaoManager;
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
        final Dao<Result, Integer> dao = (Dao<Result, Integer>) daoManager.getDaoFor(aClass);
        return ObservableCreator.create(new Callable<List<Result>>() {
            @Override
            public List<Result> call() throws Exception {
                QueryBuilder<Result, Integer> queryBuilder = dao.queryBuilder();
                queryBuilder.where().eq(columnName, value);
                PreparedQuery<Result> preparedQuery = queryBuilder.prepare();
                List<Result> list = dao.query(preparedQuery);
                if (list == null){
                    list = new ArrayList<>();
                }
                return list;
            }
        });
    }
}
