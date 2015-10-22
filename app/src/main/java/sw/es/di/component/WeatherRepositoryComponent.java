package sw.es.di.component;

import javax.inject.Singleton;

import dagger.Component;
import sw.es.di.module.AppSharedModule;
import sw.es.di.module.NetworkModule;
import sw.es.di.module.SchedulerModule;
import sw.es.model.sharedprefs.AppShared;

/**
 * Created by albertopenasamor on 22/10/15.
 */
@Singleton
@Component(
        modules = {
                NetworkModule.class,
                SchedulerModule.class,
                AppSharedModule.class
        },
        dependencies = {
                ApplicationComponent.class
        }
)
public interface WeatherRepositoryComponent {
    AppShared provideAppShared();
}
