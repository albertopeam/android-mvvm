package sw.es.di.component;

import dagger.Component;
import sw.es.di.common.PerActivity;
import sw.es.di.module.AppWidgetConfigModule;
import sw.es.di.module.shared.AppSharedModule;
import sw.es.di.module.shared.IconModule;
import sw.es.di.module.shared.SchedulerModule;
import sw.es.di.module.shared.StringTranslationModule;
import sw.es.view.activity.widgetconfig.AppWidgetConfigActivity;
import sw.es.viewmodel.appwidgetconfig.AppWidgetConfigViewModel;

/**
 * Created by albertopenasamor on 17/11/15.
 */
@PerActivity
@Component(
        dependencies = {
                ApplicationComponent.class
        },
        modules = {
                AppWidgetConfigModule.class,
                SchedulerModule.class,
                AppSharedModule.class,
                IconModule.class,
                StringTranslationModule.class

        }
)
public interface AppWidgetConfigComponent {
    void inject(AppWidgetConfigActivity activity);

    AppWidgetConfigViewModel provideAppWidgetConfigViewModel();
}
