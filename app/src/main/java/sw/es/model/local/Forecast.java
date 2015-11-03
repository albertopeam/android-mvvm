package sw.es.model.local;

import java.util.ArrayList;
import java.util.List;

import sw.es.dagger2.BuildConfig;

/**
 * Created by albertopenasamor on 3/11/15.
 */
public class Forecast {

    private long id;
    private String name;
    private List<ForecastWeather> forecastWeatherList;

    public Forecast() {
        if (BuildConfig.DEBUG) {
            forecastWeatherList = new ArrayList<>();
            forecastWeatherList.add(new ForecastWeather());
            forecastWeatherList.add(new ForecastWeather());
            forecastWeatherList.add(new ForecastWeather());
            forecastWeatherList.add(new ForecastWeather());
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name!=null?name:"";
    }

    public List<ForecastWeather> getForecastWeatherList() {
        return forecastWeatherList;
    }
}
