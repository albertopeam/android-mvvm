package sw.es.model.mapper;

import sw.es.model.database.entity.WeatherEntity;
import sw.es.model.local.Weather;
import sw.es.model.mapper.interfaces.Mapper;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public class WeatherEntityMapper implements Mapper<WeatherEntity, Weather> {

    @Override
    public Weather map(WeatherEntity weatherEntity) {
        Weather weather = new Weather();
        weather.setId(weatherEntity.getRemoteId());
        weather.setHumidity(weatherEntity.getHumidity());
        weather.setLatitude(weatherEntity.getLatitude());
        weather.setLongitude(weatherEntity.getLongitude());
        weather.setPressure(weatherEntity.getPressure());
        weather.setTemp(weatherEntity.getTemp());
        weather.setWindSpeed(weatherEntity.getWindSpeed());
        weather.setName(weatherEntity.getName());
        return weather;
    }

    @Override
    public WeatherEntity remap(Weather weather) {
        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setWindSpeed(weather.getWindSpeed());
        weatherEntity.setTemp(weather.getTemp());
        weatherEntity.setPressure(weather.getPressure());
        weatherEntity.setLongitude(weather.getLongitude());
        weatherEntity.setLatitude(weather.getLatitude());
        weatherEntity.setHumidity(weather.getHumidity());
        weatherEntity.setRemoteId(weather.getRemoteId());
        weatherEntity.setName(weather.getName());
        return weatherEntity;
    }
}