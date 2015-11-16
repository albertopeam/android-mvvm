package sw.es.model.mapper.forecast;


import java.util.ArrayList;
import java.util.List;

import sw.es.model.backend.ForecastCityCloud;
import sw.es.model.backend.ForecastCloud;
import sw.es.model.local.Forecast;
import sw.es.model.local.ForecastWeather;
import sw.es.model.mapper.interfaces.Mapper;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public class ForecastCityCloudMapper implements Mapper<ForecastCityCloud, Forecast> {


    @Override
    public Forecast map(ForecastCityCloud forecastCityCloud) {
        Forecast forecast = new Forecast();
        forecast.setName(forecastCityCloud.getCityName());
        forecast.setId(forecastCityCloud.getCityId());

        ForecastCloudMapper mapper = new ForecastCloudMapper();
        List<ForecastWeather> forecastWeatherList = new ArrayList<>();
        for (ForecastCloud forecastCloud:forecastCityCloud.getList()){
            ForecastWeather forecastWeather = mapper.map(forecastCloud);
            forecastWeatherList.add(forecastWeather);
        }
        forecast.setForecastWeatherList(forecastWeatherList);

        return forecast;
    }

    @Override
    public ForecastCityCloud remap(Forecast forecast) {
        throw new UnsupportedOperationException();
    }

}
