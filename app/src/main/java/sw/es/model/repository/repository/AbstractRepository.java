package sw.es.model.repository.repository;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Action1;
import sw.es.model.repository.criteria.LoadCriteria;
import sw.es.model.repository.criteria.StoreCriteria;
import sw.es.model.repository.datastore.DataStore;
import sw.es.model.repository.datastore.DataStoreFactory;
import sw.es.model.repository.exception.NoMoreCriteriaException;
import sw.es.model.repository.exception.NotFoundInDataStoreException;


/**
 * Created by albertopenasamor on 22/10/15.
 */
//TODO: gestionar la parada de los observables....Subscription....
public class AbstractRepository<Model, Params> implements Repository<Model, Params> {


    private DataStoreFactory dataStoreFactory;
    private Scheduler publishScheduler;


    public AbstractRepository(DataStoreFactory dataStoreFactory, Scheduler publishScheduler) {
        this.dataStoreFactory = dataStoreFactory;
        this.publishScheduler = publishScheduler;
    }


    @Override
    public void fetch(final Params params,final LoadCriteria loadCriteria, final FetchCallback<Model, Params> callback) {
        DataStore dataStore = dataStoreFactory.get(loadCriteria);
        Observable<Model> fetchObservable = dataStore.fetch(params);
        fetchObservable.subscribeOn(publishScheduler)
                .subscribe(new Action1<Model>() {
                    @Override
                    public void call(Model model) {
                        callback.onFetch(params, loadCriteria, model);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (throwable instanceof NotFoundInDataStoreException) {
                            try {
                                LoadCriteria newLoadCriteria = loadCriteria.next();
                                fetch(params, newLoadCriteria, callback);
                            } catch (NoMoreCriteriaException e) {
                                callback.onFetchError(params, loadCriteria, e);
                            }
                        }else{
                            callback.onFetchError(params, loadCriteria, throwable);
                        }
                    }
                });
    }


    @Override
    public Observable<Model> pull(Params params, LoadCriteria loadCriteria) {
        throw new UnsupportedOperationException();
        //TODO: 2 ops de nivel de abstracción repo, mejor eliminar
    }

    @Override
    public void commit(Model model, StoreCriteria storeCriteria, CommitCallback<Model, Params>callback) {
        //TODO: 1 op de nivel de abstracción datastore, (salva en los datastore locales....)
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<Boolean> push(Model model, StoreCriteria storeCriteria) {
        throw new UnsupportedOperationException();
    }

}
