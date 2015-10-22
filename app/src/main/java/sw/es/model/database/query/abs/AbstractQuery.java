package sw.es.model.database.query.abs;


import sw.es.model.database.core.DaoManager;

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
