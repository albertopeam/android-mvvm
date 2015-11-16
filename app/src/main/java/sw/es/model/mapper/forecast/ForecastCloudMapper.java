package sw.es.model.mapper.forecast;


import sw.es.model.backend.ForecastCloud;
import sw.es.model.local.ForecastWeather;
import sw.es.model.mapper.interfaces.Mapper;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public class ForecastCloudMapper implements Mapper<ForecastCloud, ForecastWeather> {


    @Override
    public ForecastWeather map(ForecastCloud forecastCloud) {
        ForecastWeather forecastWeather = new ForecastWeather();
        forecastWeather.setCloudiness(forecastCloud.getCloudiness());
        forecastWeather.setDatetime(forecastCloud.getDateTime());
        forecastWeather.setDescription(forecastCloud.getDescription());
        forecastWeather.setHumidity(forecastCloud.getHumidity());
        forecastWeather.setIcon(forecastCloud.getIcon());
        forecastWeather.setIconId(forecastCloud.getIconId());
        forecastWeather.setPressure(forecastCloud.getPressure());
        forecastWeather.setTemp(forecastCloud.getTemp());
        forecastWeather.setTempMax(forecastCloud.getTempMax());
        forecastWeather.setTempMin(forecastCloud.getTempMin());
        forecastWeather.setWindDegree(forecastCloud.getWindDegree());
        forecastWeather.setWindSpeed(forecastCloud.getWindSpeed());
        return forecastWeather;
    }

    @Override
    public ForecastCloud remap(ForecastWeather forecastWeather) {
        throw new UnsupportedOperationException();
    }
}
