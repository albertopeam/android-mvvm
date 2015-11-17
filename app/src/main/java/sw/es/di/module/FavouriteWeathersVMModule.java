package sw.es.di.module;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import sw.es.di.common.ExecutionScheduler;
import sw.es.di.common.ListenScheduler;
import sw.es.di.common.PerActivity;
import sw.es.domain.repository.weather.repo.CloudWeatherDataStore;
import sw.es.domain.repository.weather.repo.DBWeatherDataStore;
import sw.es.domain.repository.weather.repo.WeatherDataStoreFactory;
import sw.es.domain.repository.weather.repo.WeatherOutdate;
import sw.es.domain.repository.weather.repo.WeatherRepository;
import sw.es.domain.repository.weather.usecase.WeatherPullUseCase;
import sw.es.domain.repository.weather.usecase.WeatherRemoveUseCase;
import sw.es.domain.sharedprefs.AppShared;
import sw.es.domain.sharedprefs.usecase.FetchFavouritesLocationsUseCase;
import sw.es.domain.sharedprefs.usecase.RemoveFavouriteLocationUseCase;
import sw.es.domain.sharedprefs.usecase.StoreFavouriteLocationUseCase;
import sw.es.network.WeatherBackendAPI;
import sw.es.viewmodel.weather.FavouriteWeathersViewModel;

@Module
public class FavouriteWeathersVMModule {


    public FavouriteWeathersVMModule() {}


    @Provides
    @PerActivity
    FavouriteWeathersViewModel provideWeatherViewModel(WeatherPullUseCase weatherPullUseCase, WeatherRemoveUseCase weatherRemoveUseCase, FetchFavouritesLocationsUseCase fetchFavouritesLocationsUseCase, StoreFavouriteLocationUseCase storeFavouriteLocationUseCase, RemoveFavouriteLocationUseCase removeFavouriteLocationUseCase){
        return new FavouriteWeathersViewModel(weatherPullUseCase, weatherRemoveUseCase, fetchFavouritesLocationsUseCase, storeFavouriteLocationUseCase, removeFavouriteLocationUseCase);
    }


    @Provides
    @PerActivity
    WeatherRemoveUseCase provideRemoveUseCase(WeatherRepository weatherRepository){
        return new WeatherRemoveUseCase(weatherRepository);
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


    @Provides
    @PerActivity
    RemoveFavouriteLocationUseCase provideRemoveFavouriteLocationUseCase(AppShared appShared){
        return new RemoveFavouriteLocationUseCase(appShared);
    }
}
