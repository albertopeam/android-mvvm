package sw.es.repository.weather;

import javax.inject.Inject;
import javax.inject.Singleton;

import sw.es.repository.interfaces.Outdate;

/**
 * Created by albertopenasamor on 27/5/15.
 */
@Singleton
public class WeatherDataStoreFactory {

    private CloudWeatherDataStore cloudWeatherDataStore;
    private DatabaseWeatherDataStore databaseWeatherDataStore;

    private WeatherOutdate weatherOutdate;

    @Inject
    public WeatherDataStoreFactory(CloudWeatherDataStore cloudWeatherDataStore, DatabaseWeatherDataStore databaseWeatherDataStore, Outdate outdate) {
        this.cloudWeatherDataStore = cloudWeatherDataStore;
        this.databaseWeatherDataStore = databaseWeatherDataStore;
        this.weatherOutdate = weatherOutdate;
    }

    public DataStore get(long id, FetchCriteria fetchCriteria) {
        if (fetchCriteria.getFetchCriteria().name().equals(FetchCriteria.FetchCriteriaEnum.GET.name())) {
            if (weatherOutdate.isExpired(id)) {
                try {
                    fetchCriteria.next();
                } catch (NoMoreCriteriaException e) {
                    e.printStackTrace();
                }
                return cloudWeatherDataStore;
            }
            return databaseWeatherDataStore;
        } else if (fetchCriteria.getFetchCriteria().name().equals(FetchCriteria.FetchCriteriaEnum.REFRESH.name())) {
            return cloudWeatherDataStore;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public DataStore get(StoreCriteria storeCriteria) {
        if (storeCriteria.name().equals(StoreCriteria.SAVE.name())) {
            return databaseWeatherDataStore;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
