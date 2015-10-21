package sw.es.model.repository.weather;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Scheduler;
import sw.es.model.local.Weather;
import sw.es.model.repository.datastore.DataStore;

/**
 * Created by albertopenasamor on 27/5/15.
 */
@Singleton
public class DBWeatherDataStore implements DataStore<Weather, String> {

    todo: seguir aqui
    todo: traerme lib nueva de db

    private Scheduler executionScheduler;


    @Inject
    public DBWeatherDataStore(Scheduler executionScheduler) {
        this.executionScheduler = executionScheduler;
    }

    @Override
    public Observable<Weather> fetch(String s) {
        return null;
    }

    @Override
    public Observable<Weather> pull(String s) {
        return null;
    }

    @Override
    public Observable<Boolean> commit(Weather weather) {
        return null;
    }

    @Override
    public Observable<Boolean> push(Weather weather) {
        return null;
    }
}
