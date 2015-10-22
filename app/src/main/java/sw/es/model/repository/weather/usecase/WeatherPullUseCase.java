package sw.es.model.repository.weather.usecase;

import javax.inject.Inject;
import javax.inject.Singleton;

import sw.es.model.local.Weather;
import sw.es.model.repository.repository.Repository;
import sw.es.model.repository.usecase.PullUseCase;

/**
 * Created by albertopenasamor on 22/10/15.
 */
@Singleton
public class WeatherPullUseCase extends PullUseCase<Weather, String> {

    @Inject
    public WeatherPullUseCase(Repository<Weather, String> repository) {
        super(repository);
    }
}
