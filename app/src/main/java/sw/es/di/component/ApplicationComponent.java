
package sw.es.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import sw.es.android.AndroidApp;
import sw.es.di.common.ForApplication;
import sw.es.di.module.ApplicationModule;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(AndroidApp androidApp);
    @ForApplication Context provideAppContext();
}
