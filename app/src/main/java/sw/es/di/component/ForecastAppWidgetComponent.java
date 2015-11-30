package sw.es.di.component;

import dagger.Component;
import sw.es.appwidget.ForecastAppWidget;
import sw.es.appwidget.ForecastAppWidgetService;
import sw.es.di.common.PerService;
import sw.es.di.module.ForecastAppWidgetModule;
import sw.es.di.module.shared.AppSharedModule;
import sw.es.di.module.shared.IconModule;
import sw.es.di.module.shared.NetworkModule;
import sw.es.di.module.shared.SchedulerModule;
import sw.es.di.module.shared.StringTranslationModule;

/**
 * Created by albertopenasamor on 2/11/15.
 */
@PerService
@Component(
        dependencies = {
                ApplicationComponent.class
        },
        modules = {
                ForecastAppWidgetModule.class,
                NetworkModule.class,
                SchedulerModule.class,
                AppSharedModule.class,
                IconModule.class,
                StringTranslationModule.class
        }
)
public interface ForecastAppWidgetComponent {
    void inject(ForecastAppWidgetService forecastAppWidgetService);
    void inject(ForecastAppWidget currentWeatherAppWidget);
}
