package sw.es.android;

import android.app.Application;
import android.util.Log;

import sw.es.dagger2.BuildConfig;
import sw.es.di.component.ApplicationComponent;
import sw.es.di.module.ApplicationModule;
import sw.es.di.DaggerApplicationComponent;

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
        initializeInjector();
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
