package es.sw.activerecordapp.database.query;

import com.j256.ormlite.dao.Dao;

import es.sw.activerecordapp.database.core.CreateOrUpdateHelper;
import es.sw.activerecordapp.database.core.DaoManager;
import es.sw.activerecordapp.database.model.Entity;
import es.sw.activerecordapp.database.query.abs.AbstractQuery;

/**
 * Created by alberto on 18/10/15.
 */
public class Save<Result extends Entity> extends AbstractQuery<Boolean> {


    private Result result;


    public Save(Result result) {
        this.result = result;
    }


    @Override
    protected Boolean query(DaoManager daoManager) throws Exception {
        boolean success = false;
        Dao<Result,Integer> dao = daoManager.getDaoFor(result.getClass());
        success = CreateOrUpdateHelper.isCreatedOrUpdated(dao.createOrUpdate(result));
        return success;
    }
}
