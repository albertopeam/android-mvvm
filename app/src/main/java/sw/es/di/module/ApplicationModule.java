package sw.es.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import sw.es.android.AndroidApp;
import sw.es.di.common.ForApplication;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {

    private final AndroidApp application;

    public ApplicationModule(AndroidApp application) {
        this.application = application;
    }

    @Provides
    @ForApplication
    Context provideApplicationContext() {
        return this.application;
    }

}
