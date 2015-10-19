package sw.es.repository.weather;

import javax.inject.Singleton;

import rx.Observable;
import sw.es.repository.criteria.FetchCriteria;
import sw.es.repository.criteria.StoreCriteria;
import sw.es.repository.interfaces.Outdate;
import sw.es.repository.interfaces.Repository;


/**
 * Created by albertopenasamor on 27/5/15.
 */
@Singleton
public abstract class AbstractRepository<Model> implements Repository<Model> {

    private es.sw.repositorysample.repository.weather.WeatherDataStoreFactory weatherDataStoreFactory;
    private Outdate outdate;


    @Override
    public Observable<Model> findById(long id, FetchCriteria fetchCriteria) {
        return null;
    }

    @Override
    public Observable<Boolean> save(Model weather, StoreCriteria storeCriteria){
        return false;
    }
}
