package sw.es.model.repository.weather.repo;

import javax.inject.Inject;

import rx.Scheduler;
import sw.es.di.common.Named;
import sw.es.di.module.SchedulerModule;
import sw.es.model.local.Weather;
import sw.es.model.repository.repository.AbstractRepository;


/**
 * Created by albertopenasamor on 27/5/15.
 */
public class WeatherRepository extends AbstractRepository<Weather,String> {

    @Inject
    public WeatherRepository(WeatherDataStoreFactory weatherDataStoreFactory, WeatherOutdate weatherOutdate, @Named(SchedulerModule.PUBLISH) Scheduler scheduler) {
        super(weatherDataStoreFactory, weatherOutdate, scheduler);
    }

}
