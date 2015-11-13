package sw.es.model.repository.weather.repo;

import javax.inject.Inject;

import rx.Scheduler;
import sw.es.model.local.Weather;
import sw.es.model.repository.repo.repository.AbstractRepository;


/**
 * Created by albertopenasamor on 27/5/15.
 */
public class WeatherRepository extends AbstractRepository<Weather,String> {

    @Inject
    public WeatherRepository(WeatherDataStoreFactory weatherDataStoreFactory, WeatherOutdate weatherOutdate, Scheduler scheduler) {
        super(weatherDataStoreFactory, weatherOutdate, scheduler);
    }

}
