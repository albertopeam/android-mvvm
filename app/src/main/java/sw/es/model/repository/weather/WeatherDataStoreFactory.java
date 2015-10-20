package sw.es.model.repository.weather;

import javax.inject.Singleton;

import sw.es.model.local.Weather;
import sw.es.model.repository.datastore.AbsDataStoreFactory;
import sw.es.model.repository.datastore.CloudDataStore;
import sw.es.model.repository.datastore.DBDataStore;
import sw.es.model.repository.outdate.Outdate;

/**
 * Created by albertopenasamor on 27/5/15.
 */
@Singleton
public class WeatherDataStoreFactory extends AbsDataStoreFactory<Weather>{

    public WeatherDataStoreFactory(CloudDataStore<Weather> cloudDataStore, DBDataStore<Weather> dbDataStore, Outdate<Weather> outdate) {
        super(cloudDataStore, dbDataStore, outdate);
    }

}
