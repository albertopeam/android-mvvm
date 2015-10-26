package sw.es.di.module;

import dagger.Module;
import dagger.Provides;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
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

//TODO: usar el modulo de los scheduler, quedó relegado con los qualifiers por algún error a la hora de montar el modulo(me faltaba este MODULO en el componente!!!!)
//TODO: meter al constructor de WeatherOutdate el param, está por un setter
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
    WeatherRepository provideWeatherRepository(WeatherDataStoreFactory weatherDataStoreFactory, WeatherOutdate weatherOutdate){
        return new WeatherRepository(weatherDataStoreFactory, weatherOutdate, AndroidSchedulers.mainThread());
    }


    @Provides
    @PerActivity
    WeatherDataStoreFactory provideWeatherDataStoreFactory(CloudWeatherDataStore cloudWeatherDataStore, DBWeatherDataStore dbWeatherDataStore, WeatherOutdate weatherOutdate){
        return new WeatherDataStoreFactory(cloudWeatherDataStore, dbWeatherDataStore, weatherOutdate);
    }


    @Provides
    CloudWeatherDataStore provideCloudWeatherDataStore(WeatherBackendAPI weatherBackendAPI){
        return new CloudWeatherDataStore(weatherBackendAPI, Schedulers.io());
    }


    @Provides
    @PerActivity
    DBWeatherDataStore provideDbWeatherDataStore(){
        return new DBWeatherDataStore(Schedulers.io());
    }


    @Provides
    @PerActivity
    WeatherOutdate provideWeatherOutdate(AppShared appShared){
        int minutesBetweenUpdates = 60;
        WeatherOutdate weatherOutdate = new WeatherOutdate(appShared);
        weatherOutdate.setTimeBetweenUpdates(minutesBetweenUpdates);
        return weatherOutdate;
    }
}
