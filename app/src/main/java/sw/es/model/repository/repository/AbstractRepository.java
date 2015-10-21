package sw.es.model.repository.repository;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Action1;
import sw.es.model.repository.callback.FetchCallback;
import sw.es.model.repository.criteria.FetchCriteria;
import sw.es.model.repository.criteria.StoreCriteria;
import sw.es.model.repository.datastore.DataStore;
import sw.es.model.repository.datastore.DataStoreFactory;
import sw.es.model.repository.exceptions.NoMoreCriteriaException;
import sw.es.model.repository.exceptions.NotFoundInRepositoryException;


/**
 * Created by albertopenasamor on 27/5/15.
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
    public void fetch(final Params params,final FetchCriteria fetchCriteria, final FetchCallback<Model, Params> callback) {
        DataStore dataStore = dataStoreFactory.get(fetchCriteria);
        Observable<Model> fetchObservable = dataStore.fetch(params);
        fetchObservable.subscribeOn(publishScheduler)
                .subscribe(new Action1<Model>() {
                    @Override
                    public void call(Model model) {
                        callback.onFetch(params, fetchCriteria, model);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (throwable instanceof NotFoundInRepositoryException) {
                            try {
                                FetchCriteria newFetchCriteria = fetchCriteria.next();
                                fetch(params, newFetchCriteria, callback);
                            } catch (NoMoreCriteriaException e) {
                                callback.onFetchError(params, fetchCriteria, e);
                            }
                        }else{
                            callback.onFetchError(params, fetchCriteria, throwable);
                        }
                    }
                });
    }


    @Override
    public Observable<Model> pull(Params params, FetchCriteria fetchCriteria) {
        todo
        return null;
    }

    @Override
    public Observable<Boolean> commit(Model model, StoreCriteria storeCriteria) {
        todo
        return null;
    }

    @Override
    public Observable<Boolean> push(Model model, StoreCriteria storeCriteria) {
        return null;
    }

}
