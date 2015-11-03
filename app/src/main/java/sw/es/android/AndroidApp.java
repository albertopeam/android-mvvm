package sw.es.android;

import android.app.Application;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.WeathericonsModule;

import sw.es.dagger2.BuildConfig;
import sw.es.di.component.ApplicationComponent;
import sw.es.di.component.DaggerApplicationComponent;
import sw.es.di.module.ApplicationModule;
import sw.es.model.database.DatabaseHelper;
import sw.es.model.database.core.DatabaseManager;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public class AndroidApp extends Application {

    private static final String TAG = AndroidApp.class.getSimpleName();
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onCreate");
        }
        super.onCreate();


        initDatabase();
        initializeInjector();
        initIcons();
    }

    private void initIcons() {
        Iconify.with(new WeathericonsModule());
    }


    private void initDatabase(){
        DatabaseHelper helper = OpenHelperManager.getHelper(getApplicationContext(), DatabaseHelper.class);
        DatabaseManager.newInstance(helper);
    }


    private void initializeInjector() {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "initializeInjector");
        }


        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }


    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
