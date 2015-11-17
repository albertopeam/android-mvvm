package sw.es.domain.repository.weather.repo;

import javax.inject.Inject;

import sw.es.model.local.Weather;
import sw.es.domain.repository.repo.datastore.AbsDataStoreFactory;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public class WeatherDataStoreFactory extends AbsDataStoreFactory<Weather, String>{

    @Inject
    public WeatherDataStoreFactory(CloudWeatherDataStore cloudDataStore, DBWeatherDataStore dbDataStore, WeatherOutdate outdate) {
        super(cloudDataStore, dbDataStore, outdate);
    }

}
