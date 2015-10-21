package sw.es.model.mapper;


import sw.es.model.backend.WeatherCloud;
import sw.es.model.local.Weather;
import sw.es.model.mapper.interfaces.CloudMapper;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public class WeatherCloudMapper implements CloudMapper<WeatherCloud, Weather> {

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
        return weather;
    }
}
