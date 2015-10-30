package sw.es.model.database.query;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import sw.es.model.database.core.DaoManager;
import sw.es.model.database.exception.DeleteException;
import sw.es.model.database.model.Entity;
import sw.es.model.database.query.abs.AbstractQuery;

/**
 * Created by albertopenasamor on 30/10/15.
 */
public class DeleteWhere<Result extends Entity> extends AbstractQuery<Boolean> {


    private Class aClass;
    private String columnName;
    private Object value;


    public DeleteWhere(Class aClass, String columnName, Object value) {
        this.aClass = aClass;
        this.columnName = columnName;
        this.value = value;
    }


    @Override
    protected Boolean query(DaoManager daoManager) throws Exception{
        Dao<Result, Integer> dao = daoManager.getDaoFor(aClass);
        DeleteBuilder<Result, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(columnName, value);
        int deletedRows = deleteBuilder.delete();
        if (deletedRows == 1){
            return true;
        }else{
            throw new DeleteException(aClass, deletedRows, 1);
        }
    }
}
