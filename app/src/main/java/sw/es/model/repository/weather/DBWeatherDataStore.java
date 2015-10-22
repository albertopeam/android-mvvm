package sw.es.model.repository.weather;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import sw.es.model.database.entity.WeatherEntity;
import sw.es.model.database.rxquery.RxWhere;
import sw.es.model.local.Weather;
import sw.es.model.repository.datastore.DataStore;

/**
 * Created by albertopenasamor on 27/5/15.
 */
@Singleton
public class DBWeatherDataStore implements DataStore<Weather, String> {

    todo: seguir aqui

    private Scheduler executionScheduler;


    @Inject
    public DBWeatherDataStore(Scheduler executionScheduler) {
        this.executionScheduler = executionScheduler;
    }

    @Override
    public Observable<Weather> fetch(String s) {
        RxWhere<WeatherEntity>rxWhere = new RxWhere<>(WeatherEntity.class, WeatherEntity.COLUMN_NAME, s);
        return rxWhere.run()
                .flatMap(new Func1<List<WeatherEntity>, Observable<WeatherEntity>>() {
                    @Override
                    public Observable<WeatherEntity> call(List<WeatherEntity> weatherEntities) {
                        todo
                        return null;
                    }
                })
                .flatMap(new Func1<WeatherEntity, Observable<Weather>>() {
                    @Override
                    public Observable<Weather> call(WeatherEntity weatherEntity) {
                        todo
                        return null;
                    }
                })
                .subscribeOn(executionScheduler);
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
