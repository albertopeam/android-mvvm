package sw.es.di.module;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import sw.es.di.common.ExecutionScheduler;
import sw.es.di.common.ListenScheduler;
import sw.es.di.common.PerActivity;
import sw.es.model.repository.weather.repo.CloudWeatherDataStore;
import sw.es.model.repository.weather.repo.DBWeatherDataStore;
import sw.es.model.repository.weather.repo.WeatherDataStoreFactory;
import sw.es.model.repository.weather.repo.WeatherOutdate;
import sw.es.model.repository.weather.repo.WeatherRepository;
import sw.es.model.repository.weather.usecase.WeatherPullUseCase;
import sw.es.model.sharedprefs.AppShared;
import sw.es.model.usecase.FetchFavouritesLocationsUseCase;
import sw.es.network.WeatherBackendAPI;
import sw.es.viewmodel.weather.FavouriteWeathersViewModel;

//TODO: usar el modulo de los scheduler, quedó relegado con los qualifiers por algún error a la hora de montar el modulo(me faltaba este MODULO en el componente!!!!)
//TODO: meter al constructor de WeatherOutdate el param, está por un setter
@Module
public class FavouriteWeathersViewModelModule {


    public FavouriteWeathersViewModelModule() {}


    @Provides
    @PerActivity
    FavouriteWeathersViewModel provideWeatherViewModel(WeatherPullUseCase weatherPullUseCase, FetchFavouritesLocationsUseCase fetchFavouritesLocationsUseCase){
        return new FavouriteWeathersViewModel(weatherPullUseCase, fetchFavouritesLocationsUseCase);
    }


    @Provides
    @PerActivity
    WeatherPullUseCase providePullUseCase(WeatherRepository weatherRepository){
        return new WeatherPullUseCase(weatherRepository);
    }


    @Provides
    @PerActivity
    WeatherRepository provideWeatherRepository(WeatherDataStoreFactory weatherDataStoreFactory, WeatherOutdate weatherOutdate, @ListenScheduler Scheduler scheduler){
        return new WeatherRepository(weatherDataStoreFactory, weatherOutdate, scheduler);
    }


    @Provides
    @PerActivity
    WeatherDataStoreFactory provideWeatherDataStoreFactory(CloudWeatherDataStore cloudWeatherDataStore, DBWeatherDataStore dbWeatherDataStore, WeatherOutdate weatherOutdate){
        return new WeatherDataStoreFactory(cloudWeatherDataStore, dbWeatherDataStore, weatherOutdate);
    }


    @Provides
    CloudWeatherDataStore provideCloudWeatherDataStore(WeatherBackendAPI weatherBackendAPI, @ExecutionScheduler Scheduler scheduler){
        return new CloudWeatherDataStore(weatherBackendAPI, scheduler);
    }


    @Provides
    @PerActivity
    DBWeatherDataStore provideDbWeatherDataStore(@ExecutionScheduler Scheduler scheduler){
        return new DBWeatherDataStore(scheduler);
    }


    @Provides
    @PerActivity
    WeatherOutdate provideWeatherOutdate(AppShared appShared){
        int minutesBetweenUpdates = 60;
        WeatherOutdate weatherOutdate = new WeatherOutdate(appShared, minutesBetweenUpdates);
        return weatherOutdate;
    }


    @Provides
    @PerActivity
    FetchFavouritesLocationsUseCase provideFetchFavouritesLocationsUseCase(AppShared appShared, @ExecutionScheduler Scheduler executionScheduler, @ListenScheduler Scheduler listenScheduler){
        return new FetchFavouritesLocationsUseCase(appShared, executionScheduler, listenScheduler);
    }
}
