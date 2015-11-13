package sw.es.model.repository.forecast;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import sw.es.model.backend.ForecastCityCloud;
import sw.es.model.local.Forecast;
import sw.es.model.local.Weather;
import sw.es.model.mapper.weather.WeatherCloudMapper;
import sw.es.model.repository.repo.datastore.CloudDataStore;
import sw.es.model.repository.repo.exception.NotFoundInCloudDataStoreException;
import sw.es.model.rx.ObservableCreator;
import sw.es.network.WeatherBackendAPI;


/**
 * Created by albertopenasamor on 27/5/15.
 */
public class CloudForecastCityDataStore extends CloudDataStore<Forecast, String> {


    private WeatherBackendAPI weatherBackendAPI;
    private Scheduler executionScheduler;


    @Inject
    public CloudForecastCityDataStore(WeatherBackendAPI weatherBackendAPI, Scheduler executionScheduler) {
        this.weatherBackendAPI = weatherBackendAPI;
        this.executionScheduler = executionScheduler;
    }

    @Override
    public Observable<Forecast> fetch(final String name) {
        Observable<Forecast> forecastObservable = weatherBackendAPI.fetchForecast(name).flatMap(new Func1<ForecastCityCloud, Observable<?>>() {
            @Override
            public Observable<?> call(final ForecastCityCloud forecastCityCloud) {
                return ObservableCreator.create(new Callable<Weather>() {
                    @Override
                    public Weather call() throws Exception {
                        if (forecastCityCloud.isSuccess()) {
                            todo: mapper, ya creado el paquete...
                            WeatherCloudMapper mapper = new WeatherCloudMapper();
                            return mapper.map(forecastCityCloud);
                        }
                        throw new NotFoundInCloudDataStoreException(CloudForecastCityDataStore.class);
                    }
                });
            }
        }).subscribeOn(executionScheduler);
        return forecastObservable;
    }


    @Override
    public Observable<Boolean> commit(final Forecast forecast) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<Boolean> remove(String name) {
        throw new UnsupportedOperationException();
    }

}
