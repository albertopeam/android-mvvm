package sw.es.model.repository.weather.usecase;

import javax.inject.Inject;

import sw.es.model.local.Weather;
import sw.es.model.repository.usecase.RemoveUseCase;
import sw.es.model.repository.weather.repo.WeatherRepository;

/**
 * Created by albertopenasamor on 22/10/15.
 */
public class WeatherRemoveUseCase extends RemoveUseCase<Weather, String> {

    @Inject
    public WeatherRemoveUseCase(WeatherRepository weatherRepository) {
        super(weatherRepository);
    }
}
