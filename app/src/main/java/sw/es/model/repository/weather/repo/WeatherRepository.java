package sw.es.model.repository.weather.repo;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Scheduler;
import sw.es.model.local.Weather;
import sw.es.model.repository.repository.AbstractRepository;


/**
 * Created by albertopenasamor on 27/5/15.
 */
@Singleton
public class WeatherRepository extends AbstractRepository<Weather,String> {

    @Inject
    public WeatherRepository(WeatherDataStoreFactory weatherDataStoreFactory,  Scheduler scheduler) {
        super(weatherDataStoreFactory, scheduler);
    }

}
