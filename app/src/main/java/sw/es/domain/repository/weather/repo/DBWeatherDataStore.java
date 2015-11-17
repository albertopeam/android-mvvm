package sw.es.domain.repository.weather.repo;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import sw.es.model.database.entity.WeatherEntity;
import sw.es.model.database.rxquery.RxDeleteWhere;
import sw.es.model.database.rxquery.RxSave;
import sw.es.model.database.rxquery.RxWhere;
import sw.es.model.local.Weather;
import sw.es.model.mapper.weather.WeatherEntityMapper;
import sw.es.domain.repository.repo.datastore.DBDataStore;
import sw.es.domain.repository.repo.exception.NotFoundInDBDataStoreException;
import sw.es.domain.rx.ObservableCreator;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public class DBWeatherDataStore extends DBDataStore<Weather, String> {


    private Scheduler executionScheduler;


    @Inject
    public DBWeatherDataStore(Scheduler executionScheduler) {
        this.executionScheduler = executionScheduler;
    }


    @Override
    public Observable<Weather> fetch(final String name) {
        RxWhere<WeatherEntity>rxWhere = new RxWhere<>(WeatherEntity.class, WeatherEntity.COLUMN_NAME, name);
        return rxWhere.run()
                .flatMap(new Func1<List<WeatherEntity>, Observable<Weather>>() {
                    @Override
                    public Observable<Weather> call(final List<WeatherEntity> weatherEntities) {
                        return ObservableCreator.create(new Callable<Weather>() {
                            @Override
                            public Weather call() throws Exception {
                                if (weatherEntities.isEmpty()){
                                    throw new NotFoundInDBDataStoreException(DBWeatherDataStore.class);
                                }else{
                                    WeatherEntityMapper mapper = new WeatherEntityMapper();
                                    return mapper.map(weatherEntities.get(0));
                                }
                            }
                        });
                    }
                })
                .subscribeOn(executionScheduler);
    }

    @Override
    public Observable<Boolean> commit(final Weather weather) {
        Observable observable = ObservableCreator.create(new Callable<WeatherEntity>() {
            @Override
            public WeatherEntity call() throws Exception {
                WeatherEntityMapper mapper = new WeatherEntityMapper();
                WeatherEntity entity = mapper.remap(weather);
                return entity;
            }
        }).flatMap(new Func1<WeatherEntity, Observable<Boolean>>() {
            @Override
            public Observable<Boolean> call(WeatherEntity weatherEntity) {
                RxSave<WeatherEntity>rxSave = new RxSave<>(weatherEntity);
                return rxSave.run();
            }
        }).subscribeOn(executionScheduler);
        return observable;
    }

    @Override
    public Observable<Boolean> remove(String name) {
        RxDeleteWhere<WeatherEntity> rxDeleteWhere = new RxDeleteWhere<>(WeatherEntity.class, WeatherEntity.COLUMN_NAME, name);
        return rxDeleteWhere.run()
                .subscribeOn(executionScheduler);

    }
}
