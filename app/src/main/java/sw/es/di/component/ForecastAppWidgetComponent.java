package sw.es.di.component;

import dagger.Component;
import sw.es.appwidget.publisher.AppWidgetPublisher;
import sw.es.appwidget.ForecastAppWidget;
import sw.es.appwidget.ForecastAppWidgetService;
import sw.es.appwidget.view.ForecastView;
import sw.es.di.common.PerService;
import sw.es.di.module.ForecastAppWidgetModule;
import sw.es.di.module.shared.NetworkModule;
import sw.es.di.module.shared.SchedulerModule;

/**
 * Created by albertopenasamor on 2/11/15.
 */
@PerService
@Component(
        modules = {
                ForecastAppWidgetModule.class,
                NetworkModule.class,
                SchedulerModule.class}
)
public interface ForecastAppWidgetComponent {
    void inject(ForecastAppWidgetService forecastAppWidgetService);
    void inject(ForecastAppWidget currentWeatherAppWidget);

    ForecastView provideForecastView();
    AppWidgetPublisher<ForecastAppWidget> providePublisher();
}
