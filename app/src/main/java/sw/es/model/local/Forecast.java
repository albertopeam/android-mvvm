package sw.es.model.local;

import java.util.List;

/**
 * Created by albertopenasamor on 3/11/15.
 */
public class Forecast {

    private long id;
    private String name;
    private List<ForecastWeather> forecastWeatherList;

    public Forecast() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name!=null?name:"";
    }

    public List<ForecastWeather> getForecastWeatherList() {
        return forecastWeatherList;
    }

    public void setForecastWeatherList(List<ForecastWeather> forecastWeatherList) {
        this.forecastWeatherList = forecastWeatherList;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
