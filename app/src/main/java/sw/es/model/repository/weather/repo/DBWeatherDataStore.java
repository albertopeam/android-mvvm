package sw.es.model.repository.weather.repo;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import sw.es.model.database.entity.WeatherEntity;
import sw.es.model.database.rxquery.RxSave;
import sw.es.model.database.rxquery.RxWhere;
import sw.es.model.local.Weather;
import sw.es.model.mapper.WeatherEntityMapper;
import sw.es.model.repository.datastore.DBDataStore;
import sw.es.model.repository.exception.NotFoundInDBDataStoreException;
import sw.es.model.rx.ObservableCreator;

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
    public Observable<Weather> fetch(final String s) {
        RxWhere<WeatherEntity>rxWhere = new RxWhere<>(WeatherEntity.class, WeatherEntity.COLUMN_NAME, s);
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
}
