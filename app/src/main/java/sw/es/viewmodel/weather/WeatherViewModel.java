package sw.es.viewmodel.weather;

import javax.inject.Inject;
import javax.inject.Singleton;

import sw.es.model.repository.weather.usecase.WeatherFetchUseCase;
import sw.es.model.repository.weather.usecase.WeatherPullUseCase;

/**
 * Created by alberto on 15/10/15.
 */
@Singleton
public class WeatherViewModel implements AbsWeatherViewModel {


    private WeatherListener weatherListener;
    private WeatherFetchUseCase weatherFetchUseCase;
    private WeatherPullUseCase weatherPullUseCase;


    @Inject
    public WeatherViewModel(WeatherFetchUseCase weatherFetchUseCase, WeatherPullUseCase weatherPullUseCase) {
        this.weatherFetchUseCase = weatherFetchUseCase;
        this.weatherPullUseCase = weatherPullUseCase;
    }


    @Override
    public void setup(WeatherListener weatherListener) {
        this.weatherListener = weatherListener;
    }


    @Override
    public void destroy() {
        weatherListener = null;
    }


    @Override
    public void load() {

    }

    @Override
    public void fetch(String localityName) {

    }



}
