package sw.es.di.component;

import dagger.Component;
import sw.es.di.common.PerActivity;
import sw.es.di.module.AppSharedModule;
import sw.es.model.sharedprefs.AppShared;

/**
 * Created by albertopenasamor on 22/10/15.
 */
@PerActivity
@Component(
        dependencies = {
                ApplicationComponent.class
        },
        modules = {
                AppSharedModule.class
        }
)
public interface AppSharedComponent {
    AppShared provideAppShared();
}
