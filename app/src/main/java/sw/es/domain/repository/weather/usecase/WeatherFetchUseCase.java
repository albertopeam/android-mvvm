package sw.es.domain.repository.weather.usecase;

import javax.inject.Inject;
import javax.inject.Singleton;

import sw.es.model.local.Weather;
import sw.es.domain.repository.repo.repository.Repository;
import sw.es.domain.repository.repo.usecase.FetchUseCase;

/**
 * Created by albertopenasamor on 22/10/15.
 */
@Singleton
public class WeatherFetchUseCase extends FetchUseCase<Weather, String> {

    @Inject
    public WeatherFetchUseCase(Repository<Weather, String> repository) {
        super(repository);
    }
}
