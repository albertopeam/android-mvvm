package sw.es.di.component;

import dagger.Component;
import sw.es.di.common.PerActivity;
import sw.es.di.module.FavouriteWeathersModule;
import sw.es.di.module.shared.AppSharedModule;
import sw.es.di.module.shared.NetworkModule;
import sw.es.di.module.shared.SchedulerModule;
import sw.es.view.activity.weather.FavouriteWeathersActivity;
import sw.es.viewmodel.weather.FavouriteWeathersViewModel;

/**
 * Created by albertopenasamor on 22/10/15.
 */
@PerActivity
@Component(
        dependencies = {
                ApplicationComponent.class
        },
        modules = {
                FavouriteWeathersModule.class,
                NetworkModule.class,
                SchedulerModule.class,
                AppSharedModule.class
        }
)
public interface FavouriteWeathersComponent {
    void inject(FavouriteWeathersActivity weatherActivity);

    FavouriteWeathersViewModel provideWeatherViewModel();
}
