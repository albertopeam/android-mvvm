package sw.es.di.module;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import sw.es.di.common.Named;
import sw.es.di.common.PerActivity;
import sw.es.model.repository.weather.repo.CloudWeatherDataStore;
import sw.es.model.repository.weather.repo.DBWeatherDataStore;
import sw.es.model.repository.weather.repo.WeatherDataStoreFactory;
import sw.es.model.repository.weather.repo.WeatherOutdate;
import sw.es.model.repository.weather.repo.WeatherRepository;
import sw.es.model.repository.weather.usecase.WeatherPullUseCase;
import sw.es.model.sharedprefs.AppShared;
import sw.es.network.WeatherBackendAPI;
import sw.es.viewmodel.weather.WeatherViewModel;

@Module
public class WeatherViewModelModule {


    public WeatherViewModelModule() {}


    @Provides
    @PerActivity
    WeatherViewModel provideWeatherViewModel(WeatherPullUseCase weatherPullUseCase){
        return new WeatherViewModel(weatherPullUseCase);
    }


    @Provides
    @PerActivity
    WeatherPullUseCase providePullUseCase(WeatherRepository weatherRepository){
        return new WeatherPullUseCase(weatherRepository);
    }


    @Provides
    @PerActivity
    WeatherRepository provideWeatherRepository(WeatherDataStoreFactory weatherDataStoreFactory, WeatherOutdate weatherOutdate, @Named(SchedulerModule.PUBLISH) Scheduler scheduler){
        return new WeatherRepository(weatherDataStoreFactory, weatherOutdate, scheduler);
    }


    @Provides
    @PerActivity
    WeatherDataStoreFactory provideWeatherDataStoreFactory(CloudWeatherDataStore cloudWeatherDataStore, DBWeatherDataStore dbWeatherDataStore, WeatherOutdate weatherOutdate){
        return new WeatherDataStoreFactory(cloudWeatherDataStore, dbWeatherDataStore, weatherOutdate);
    }


    @Provides
    CloudWeatherDataStore provideCloudWeatherDataStore(WeatherBackendAPI weatherBackendAPI, @Named(SchedulerModule.EXECUTION) Scheduler scheduler){
        return new CloudWeatherDataStore(weatherBackendAPI, scheduler);
    }


    @Provides
    @PerActivity
    DBWeatherDataStore provideDbWeatherDataStore(@Named(SchedulerModule.EXECUTION) Scheduler scheduler){
        return new DBWeatherDataStore(scheduler);
    }


    @Provides
    @PerActivity
    WeatherOutdate provideWeatherOutdate(AppShared appShared){
        int minutesBeforeExpire = 60;
        return new WeatherOutdate(appShared, minutesBeforeExpire);
    }
}
