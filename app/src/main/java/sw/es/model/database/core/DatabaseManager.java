package sw.es.model.database.core;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

import sw.es.model.database.exception.DatabaseNotInitializedException;


/**
 * Created by alberto on 29/9/15.
 */
todo: hacer modulo e inyectar. no hace falta.... por ahora
public class DatabaseManager {

    private static DatabaseManager databaseManager;
    private OrmLiteSqliteOpenHelper helper;


    public static DatabaseManager newInstance(OrmLiteSqliteOpenHelper helper){
        if (databaseManager == null) {
            databaseManager = new DatabaseManager(helper);
        }
        return databaseManager;
    }


    public static DatabaseManager getInstance(){
        if (databaseManager == null){
            throw new DatabaseNotInitializedException("Databasemanager");
        }
        return databaseManager;
    }


    private DatabaseManager(OrmLiteSqliteOpenHelper helper) {
        this.helper = helper;
    }


    public ConnectionSource getConnection(){
        return helper.getConnectionSource();
    }


    public OrmLiteSqliteOpenHelper getHelper(){
        return helper;
    }
}
