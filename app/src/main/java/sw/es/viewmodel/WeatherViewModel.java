package sw.es.viewmodel;

import java.util.List;

import sw.es.model.local.Weather;

/**
 * Created by alberto on 15/10/15.
 */
public class WeatherViewModel implements ViewModel {

    private WeatherListener weatherListener;


    public WeatherViewModel(WeatherListener weatherListener) {
        this.weatherListener = weatherListener;
    }

    @Override
    public void destroy() {
        weatherListener = null;
    }

    public interface WeatherListener {
        void onWeatherChanged(List<Weather> weatherList);
    }
}
