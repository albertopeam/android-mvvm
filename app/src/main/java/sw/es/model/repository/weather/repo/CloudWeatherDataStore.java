package sw.es.model.repository.weather.repo;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import sw.es.model.backend.WeatherCloud;
import sw.es.model.local.Weather;
import sw.es.model.mapper.weather.WeatherCloudMapper;
import sw.es.model.repository.repo.datastore.CloudDataStore;
import sw.es.model.repository.repo.exception.NotFoundInCloudDataStoreException;
import sw.es.model.rx.ObservableCreator;
import sw.es.network.WeatherBackendAPI;


/**
 * Created by albertopenasamor on 27/5/15.
 */
public class CloudWeatherDataStore extends CloudDataStore<Weather, String> {


    private WeatherBackendAPI weatherBackendAPI;
    private Scheduler executionScheduler;


    @Inject
    public CloudWeatherDataStore(WeatherBackendAPI weatherBackendAPI, Scheduler executionScheduler) {
        this.weatherBackendAPI = weatherBackendAPI;
        this.executionScheduler = executionScheduler;
    }

    @Override
    public Observable<Weather> fetch(final String name) {
        Observable<Weather> weatherObservable =  weatherBackendAPI.fetchWeather(name).flatMap(new Func1<WeatherCloud, Observable<Weather>>() {
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
    public Observable<Boolean> commit(final Weather weather) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<Boolean> remove(String name) {
        throw new UnsupportedOperationException();
    }

}
