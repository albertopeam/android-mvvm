package sw.es.viewmodel.weather;

import javax.inject.Inject;
import javax.inject.Singleton;

import sw.es.model.local.Weather;
import sw.es.model.repository.usecase.UseCaseCallback;
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
    //TODO: lista de localidades... por si busca otra igual y peta el save


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
        //TODO: cancelar las posibles peticiones.... subscriptions.... booleanos....
        weatherListener = null;
    }


    @Override
    public void load() {
        //TODO: buscar las habituales y tirar un pull!!!
    }

    @Override
    public void fetch(String localityName) {
        weatherPullUseCase.run(localityName, new UseCaseCallback<String, Weather>(){
            @Override
            public void onResult(String s, Weather weather) {
                if (hasView()){
                    weatherListener.onWeather(weather);
                }
            }

            @Override
            public void onResultError(String s, Throwable throwable) {
                if (hasView()) {
                    weatherListener.onWeatherError(throwable);
                }
            }
        });
    }


    private boolean hasView(){
        return weatherListener != null;
    }

}
