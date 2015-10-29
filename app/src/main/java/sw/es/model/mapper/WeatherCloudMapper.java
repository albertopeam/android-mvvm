package sw.es.model.mapper;


import sw.es.model.backend.WeatherCloud;
import sw.es.model.local.Weather;
import sw.es.model.mapper.interfaces.Mapper;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public class WeatherCloudMapper implements Mapper<WeatherCloud, Weather> {

    public Weather map(WeatherCloud weatherCloud) {
        Weather weather = new Weather();
        weather.setId(weatherCloud.getId());
        weather.setName(weatherCloud.getName());
        weather.setLatitude(weatherCloud.getLatitude());
        weather.setLongitude(weatherCloud.getLongitude());
        weather.setHumidity(weatherCloud.getHumidity());
        weather.setPressure(weatherCloud.getPressure());
        weather.setTemp(weatherCloud.getTemp());
        weather.setWindSpeed(weatherCloud.getWindSpeed());
        weather.setDatetime(weatherCloud.getDateTime());
        weather.setCloudiness(weatherCloud.getCloudiness());
        weather.setDescription(weatherCloud.getDescription());
        weather.setIcon(weatherCloud.getIcon());
        return weather;
    }

    @Override
    public WeatherCloud remap(Weather weather) {
        throw new UnsupportedOperationException();
    }
}
