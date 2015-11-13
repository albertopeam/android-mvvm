package sw.es.model.repository.weather.usecase;

import javax.inject.Inject;

import sw.es.model.local.Weather;
import sw.es.model.repository.repo.usecase.PullUseCase;
import sw.es.model.repository.weather.repo.WeatherRepository;

/**
 * Created by albertopenasamor on 22/10/15.
 */
public class WeatherPullUseCase extends PullUseCase<Weather, String> {

    @Inject
    public WeatherPullUseCase(WeatherRepository weatherRepository) {
        super(weatherRepository);
    }
}
