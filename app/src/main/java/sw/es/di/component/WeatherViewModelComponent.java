package sw.es.di.component;

import dagger.Component;
import sw.es.di.common.PerActivity;
import sw.es.di.module.AppSharedModule;
import sw.es.di.module.NetworkModule;
import sw.es.di.module.SchedulerModule;
import sw.es.di.module.WeatherViewModelModule;
import sw.es.view.WeatherActivity;
import sw.es.viewmodel.weather.WeatherViewModel;

/**
 * Created by albertopenasamor on 22/10/15.
 */
@PerActivity
@Component(
        dependencies = {
                ApplicationComponent.class
        },
        modules = {
                WeatherViewModelModule.class,
                NetworkModule.class,
                SchedulerModule.class,
                AppSharedModule.class
        }
)
public interface WeatherViewModelComponent {
    void inject(WeatherActivity weatherActivity);

    WeatherViewModel provideWeatherViewModel();
}
