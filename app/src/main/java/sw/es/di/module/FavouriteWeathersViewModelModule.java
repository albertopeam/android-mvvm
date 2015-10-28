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
import sw.es.model.usecase.StoreFavouriteLocationUseCase;
import sw.es.network.WeatherBackendAPI;
import sw.es.viewmodel.weather.FavouriteWeathersViewModel;

@Module
public class FavouriteWeathersViewModelModule {


    public FavouriteWeathersViewModelModule() {}


    @Provides
    @PerActivity
    FavouriteWeathersViewModel provideWeatherViewModel(WeatherPullUseCase weatherPullUseCase, FetchFavouritesLocationsUseCase fetchFavouritesLocationsUseCase, StoreFavouriteLocationUseCase storeFavouriteLocationUseCase){
        return new FavouriteWeathersViewModel(weatherPullUseCase, fetchFavouritesLocationsUseCase, storeFavouriteLocationUseCase);
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


    @Provides
    @PerActivity
    StoreFavouriteLocationUseCase provideStoreFavouriteLocationUseCase(AppShared appShared){
        return new StoreFavouriteLocationUseCase(appShared);
    }
}
