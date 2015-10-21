package sw.es.model.repository.weather;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Scheduler;
import sw.es.model.local.LatLng;
import sw.es.model.local.Weather;
import sw.es.model.repository.repository.AbstractRepository;


/**
 * Created by albertopenasamor on 27/5/15.
 */
@Singleton
public class WeatherRepository extends AbstractRepository<Weather,LatLng> {

    @Inject
    public WeatherRepository(WeatherDataStoreFactory weatherDataStoreFactory,  Scheduler scheduler) {
        super(weatherDataStoreFactory, scheduler);
    }

}
