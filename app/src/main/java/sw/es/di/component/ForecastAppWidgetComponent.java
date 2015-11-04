package sw.es.di.component;

import dagger.Component;
import sw.es.appwidget.AppWidgetPublisher;
import sw.es.appwidget.ForecastAppWidget;
import sw.es.appwidget.ForecastAppWidgetService;
import sw.es.appwidget.view.ForecastView;
import sw.es.di.common.PerService;
import sw.es.di.module.ForecastAppWidgetModule;

/**
 * Created by albertopenasamor on 2/11/15.
 */
@PerService
@Component(modules = ForecastAppWidgetModule.class)
public interface ForecastAppWidgetComponent {
    void inject(ForecastAppWidgetService forecastAppWidgetService);
    void inject(ForecastAppWidget currentWeatherAppWidget);

    ForecastView provideForecastView();
    AppWidgetPublisher<ForecastAppWidget> providePublisher();
}
