package es.sw.activerecordapp.database.query;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import es.sw.activerecordapp.database.core.DaoManager;
import es.sw.activerecordapp.database.query.abs.AbstractQuery;

/**
 * Created by alberto on 18/10/15.
 */
public class Where<Result> extends AbstractQuery<List<Result>> {


    private Class aClass;
    private String columnName;
    private Object value;


    public Where(Class aClass, String columnName, Object value) {
        this.aClass = aClass;
        this.columnName = columnName;
        this.value = value;
    }


    @Override
    protected List<Result> query(DaoManager daoManager) throws Exception{
        Dao<Result, Integer> dao = (Dao<Result, Integer>) daoManager.getDaoFor(aClass);
        QueryBuilder<Result, Integer> queryBuilder = dao.queryBuilder();
        queryBuilder.where().eq(columnName, value);
        PreparedQuery<Result> preparedQuery = queryBuilder.prepare();
        List<Result> list = dao.query(preparedQuery);
        if (list == null){
            list = new ArrayList<>();
        }
        return list;
    }
}
