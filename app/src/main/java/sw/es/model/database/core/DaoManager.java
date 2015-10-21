package es.sw.activerecordapp.database.core;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import es.sw.activerecordapp.database.model.Entity;

/**
 * Created by alberto on 27/9/15.
 */
public class DaoManager {


    private static final String TAG = DaoManager.class.getSimpleName();


    public DaoManager(){}


    public static<T extends Entity> Dao<T, Integer>getDao(Class clazz){
        DaoManager daoManager = new DaoManager();
        return daoManager.getDaoFor(clazz);
    }


    public<T extends Entity> Dao<T, Integer> getDaoFor(Class clazz){
        try {
            Dao<T, Integer> dao = DatabaseManager.getInstance().getHelper().getDao(clazz);
            return dao;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("cannot get Dao for class: " + clazz.getSimpleName());
        }
    }
}
