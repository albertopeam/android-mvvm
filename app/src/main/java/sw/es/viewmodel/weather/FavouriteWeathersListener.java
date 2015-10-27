package sw.es.viewmodel.weather;

import sw.es.model.local.FavouriteLocation;
import sw.es.model.local.Weather;

public interface FavouriteWeathersListener {
    void onWeather(Weather weather);
    void onWeatherError(Throwable throwable);
    void alreadyHasFavouriteLocation(FavouriteLocation favouriteLocation);
}