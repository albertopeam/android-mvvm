package sw.es.di.component;

import dagger.Component;
import sw.es.di.common.PerActivity;
import sw.es.di.module.AppSharedModule;
import sw.es.di.module.FavouriteWeathersViewModelModule;
import sw.es.di.module.NetworkModule;
import sw.es.di.module.SchedulerModule;
import sw.es.view.FavouriteWeathersActivity;
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
                FavouriteWeathersViewModelModule.class,
                NetworkModule.class,
                SchedulerModule.class,
                AppSharedModule.class
        }
)
public interface FavouriteWeathersViewModelComponent {
    void inject(FavouriteWeathersActivity weatherActivity);

    FavouriteWeathersViewModel provideWeatherViewModel();
}
