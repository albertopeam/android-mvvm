package sw.es.model.local;

import java.util.List;

/**
 * Created by albertopenasamor on 3/11/15.
 */
public class Forecast {

    private long id;
    private String name;
    private List<ForecastWeather> forecastWeatherList;

    public Forecast() {
        todo...
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
