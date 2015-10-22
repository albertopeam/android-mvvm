package sw.es.model.repository.weather.repo;

import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import sw.es.model.backend.WeatherCloud;
import sw.es.model.local.Weather;
import sw.es.model.mapper.WeatherCloudMapper;
import sw.es.model.repository.datastore.DataStore;
import sw.es.model.repository.exception.NotFoundInCloudDataStoreException;
import sw.es.model.rx.ObservableCreator;
import sw.es.network.WeatherBackendAPI;


/**
 * Created by albertopenasamor on 27/5/15.
 */
@Singleton
public class CloudWeatherDataStore implements DataStore<Weather, String> {


    private WeatherBackendAPI weatherBackendAPI;
    private Scheduler executionScheduler;


    @Inject
    public CloudWeatherDataStore(WeatherBackendAPI weatherBackendAPI, Scheduler executionScheduler) {
        this.weatherBackendAPI = weatherBackendAPI;
        this.executionScheduler = executionScheduler;
    }

    @Override
    public Observable<Weather> fetch(String s) {
        Observable<Weather> weatherObservable =  weatherBackendAPI.fetchWeather(s).flatMap(new Func1<WeatherCloud, Observable<Weather>>() {
            @Override
            public Observable<Weather> call(final WeatherCloud weatherCloud) {
                return ObservableCreator.create(new Callable<Weather>() {
                    @Override
                    public Weather call() throws Exception {
                        if (weatherCloud.isSuccess()){
                            WeatherCloudMapper mapper = new WeatherCloudMapper();
                            return mapper.map(weatherCloud);
                        }
                        throw new NotFoundInCloudDataStoreException(CloudWeatherDataStore.class);
                    }
                });
            }
        }).subscribeOn(executionScheduler);
        return weatherObservable;
    }


    @Override
    public Observable<Boolean> commit(Weather weather) {
        throw new UnsupportedOperationException();
    }
}
