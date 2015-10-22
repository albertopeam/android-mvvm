package sw.es.di.component;

import javax.inject.Singleton;

import dagger.Component;
import sw.es.di.module.SchedulerModule;
import sw.es.model.sharedprefs.AppShared;

/**
 * Created by albertopenasamor on 22/10/15.
 */
@Singleton
@Component(
        modules = {
                SchedulerModule.class
        },
        dependencies = {
                ApplicationComponent.class
        }
)
public interface AppSharedComponent {
    AppShared provideAppShared();
}
