package sw.es.di.component;

import dagger.Component;
import sw.es.di.common.PerActivity;
import sw.es.di.module.AppSharedModule;
import sw.es.di.module.FavouriteWeathersVMModule;
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
                FavouriteWeathersVMModule.class,
                NetworkModule.class,
                SchedulerModule.class,
                AppSharedModule.class
        }
)
public interface FavouriteWeathersVMComponent {
    void inject(FavouriteWeathersActivity weatherActivity);

    FavouriteWeathersViewModel provideWeatherViewModel();
}
