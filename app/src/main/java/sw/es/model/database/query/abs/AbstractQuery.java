package es.sw.activerecordapp.database.query.abs;

import es.sw.activerecordapp.database.core.DaoManager;

/**
 * Created by alberto on 18/10/15.
 */
public abstract class AbstractQuery<Result> implements Query<Result> {


    @Override
    public Result run() throws Exception{
        return query(new DaoManager());
    }


    protected abstract Result query(DaoManager daoManager) throws Exception;

}
