package es.sw.activerecordapp.database.core;

import com.j256.ormlite.misc.TransactionManager;

import java.sql.SQLException;
import java.util.concurrent.Callable;

import es.sw.activerecordapp.rx.RxHelper;
import rx.Observable;

/**
 * Created by alberto on 29/9/15.
 */
public class BulkTransaction<T> {


    public BulkTransaction() {}


    public T make(final Callable<T>callable) throws SQLException{
        return TransactionManager.callInTransaction(DatabaseManager.getInstance().getConnection(), callable);
    }


    public Observable<T> makeRX(final Callable<T>callable){
        return RxHelper.createObservable(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return TransactionManager.callInTransaction(DatabaseManager.getInstance().getConnection(), callable);
            }
        });
    }


    public static<T> T makeTransaction(final Callable<T>callable) throws SQLException{
        return new BulkTransaction<T>().make(callable);
    }


    public static<T> Observable<T> makeTransactionRX(final Callable<T>callable){
        return new BulkTransaction<T>().makeRX(callable);
    }
}
