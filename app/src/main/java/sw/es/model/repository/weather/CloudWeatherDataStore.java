package es.sw.repositorysample.repository.weather;

import org.json.JSONException;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import sw.es.model.backend.WeatherCloud;
import sw.es.model.local.LatLng;
import sw.es.model.local.Weather;
import sw.es.model.repository.exceptions.ObjectNotFoundInCloudRepositoryException;
import sw.es.model.repository.datastore.DataStore;
import sw.es.network.WeatherBackendAPI;


/**
 * Created by albertopenasamor on 27/5/15.
 */
@Singleton
public class CloudWeatherDataStore implements DataStore<Weather, LatLng> {


    private WeatherBackendAPI weatherBackendAPI;


    @Inject
    public CloudWeatherDataStore(WeatherBackendAPI weatherBackendAPI) {
        this.weatherBackendAPI = weatherBackendAPI;
    }


    @Override
    public Observable<Weather> fetch(LatLng latLng) {

        Observable<WeatherCloud> weatherCloudObservable =  weatherBackendAPI.fetchWeather(latLng.lat, latLng.lon);
        todo: tirar peticion en scheduler, falta inyectarlo
        todo: mapper a Observale<Weather> si procede y si no error y devolvemos algo asi como un null??? o empty
        return weatherBackendAPI.fetchWeather(latLng.lat, latLng.lon);
    }

    @Override
    public Observable<Weather> pull(LatLng latLng) {
        //TODO: pending
        throw new UnsupportedOperationException("CloudWeatherDataStore save method not implemented");
    }

    @Override
    public Observable<Boolean> commit(Weather weather) {
        throw new UnsupportedOperationException("CloudWeatherDataStore save method not implemented");
    }

    @Override
    public Observable<Boolean> push(Weather weather) {
        throw new UnsupportedOperationException("CloudWeatherDataStore save method not implemented");
    }
}
