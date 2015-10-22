package sw.es.model.repository.weather.repo;

import javax.inject.Inject;
import javax.inject.Singleton;

import sw.es.model.repository.datastore.AbsDataStoreFactory;
import sw.es.model.repository.datastore.CloudDataStore;
import sw.es.model.repository.datastore.DBDataStore;
import sw.es.model.repository.outdate.Outdate;

/**
 * Created by albertopenasamor on 27/5/15.
 */
@Singleton
public class WeatherDataStoreFactory extends AbsDataStoreFactory{

    peta la ostia

    @Inject
    public WeatherDataStoreFactory(CloudWeatherDataStore cloudDataStore, DBWeatherDataStore dbDataStore, WeatherOutdate outdate) {
        super(cloudDataStore, dbDataStore, outdate);
    }

}
