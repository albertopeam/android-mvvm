package es.sw.repositorysample.repository.weather;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Scheduler;
import sw.es.model.local.LatLng;
import sw.es.model.local.Weather;
import sw.es.model.repository.criteria.FetchCriteria;
import sw.es.model.repository.criteria.StoreCriteria;
import sw.es.model.repository.datastore.AbsDataStoreFactory;
import sw.es.model.repository.repository.AbstractRepository;
import sw.es.model.repository.repository.Repository;
import sw.es.model.repository.weather.WeatherDataStoreFactory;
import sw.es.model.repository.weather.WeatherOutdate;


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
