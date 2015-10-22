package sw.es.viewmodel.weather;

import sw.es.model.local.Weather;

public interface WeatherListener {
    void onWeather(Weather weather);
    void onWeatherError(Throwable throwable);
}