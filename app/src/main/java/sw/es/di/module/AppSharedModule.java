package sw.es.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import sw.es.di.common.ForApplication;
import sw.es.domain.sharedprefs.AppShared;
import sw.es.domain.sharedprefs.SharedPrefs;

@Module
public class AppSharedModule {

    public AppSharedModule() {}

    @Provides
    AppShared provideSharedPreferences(@ForApplication Context context) {
        return new SharedPrefs(context);
    }

}
