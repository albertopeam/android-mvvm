package sw.es.domain.repository.forecast.datastore;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import sw.es.model.backend.ForecastCityCloud;
import sw.es.model.local.Forecast;
import sw.es.model.mapper.forecast.ForecastCityCloudMapper;
import sw.es.domain.repository.repo.datastore.CloudDataStore;
import sw.es.domain.repository.repo.exception.NotFoundInCloudDataStoreException;
import sw.es.domain.rx.ObservableCreator;
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
        Observable<Forecast> forecastObservable = weatherBackendAPI.fetchForecast(name).flatMap(new Func1<ForecastCityCloud, Observable<Forecast>>() {
            @Override
            public Observable<Forecast> call(final ForecastCityCloud forecastCityCloud) {
                return ObservableCreator.create(new Callable<Forecast>() {
                    @Override
                    public Forecast call() throws Exception {
                        if (forecastCityCloud.isSuccess()) {
                            ForecastCityCloudMapper mapper = new ForecastCityCloudMapper();
                            Forecast forecast = mapper.map(forecastCityCloud);
                            return forecast;
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
