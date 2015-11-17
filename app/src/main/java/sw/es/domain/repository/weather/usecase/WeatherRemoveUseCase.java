package sw.es.domain.repository.weather.usecase;

import javax.inject.Inject;

import sw.es.model.local.Weather;
import sw.es.domain.repository.repo.usecase.RemoveUseCase;
import sw.es.domain.repository.weather.repo.WeatherRepository;

/**
 * Created by albertopenasamor on 22/10/15.
 */
public class WeatherRemoveUseCase extends RemoveUseCase<Weather, String> {

    @Inject
    public WeatherRemoveUseCase(WeatherRepository weatherRepository) {
        super(weatherRepository);
    }
}
