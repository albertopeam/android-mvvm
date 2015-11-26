package sw.es.domain.repository.repo.repository;

import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action1;
import sw.es.domain.repository.repo.criteria.LoadCriteria;
import sw.es.domain.repository.repo.criteria.RemoveCriteria;
import sw.es.domain.repository.repo.criteria.StoreCriteria;
import sw.es.domain.repository.repo.datastore.DataStore;
import sw.es.domain.repository.repo.datastore.DataStoreFactory;
import sw.es.domain.repository.repo.exception.CriteriaExpiredException;
import sw.es.domain.repository.repo.exception.NoMoreCriteriaException;
import sw.es.domain.repository.repo.outdate.Outdate;


/**
 * Created by albertopenasamor on 22/10/15.
 */
//TODO: el commit y el remove solo tienen un datastore
public class AbstractRepository<Model, Params> implements Repository<Model, Params> {


    private DataStoreFactory<Params> dataStoreFactory;
    private Outdate<Params, Model>outdate;
    private Scheduler publishScheduler;


    public AbstractRepository(DataStoreFactory<Params> dataStoreFactory, Outdate<Params, Model> outdate, Scheduler publishScheduler) {
        this.dataStoreFactory = dataStoreFactory;
        this.outdate = outdate;
        this.publishScheduler = publishScheduler;
    }


    @Override
    public Subscription fetch(final Params params, final LoadCriteria loadCriteria, final LoadCallback<Model, Params> callback) {
        try {
            DataStore dataStore = dataStoreFactory.get(loadCriteria, params);
            Observable<Model> fetchObservable = dataStore.fetch(params);
            Subscription subscription = fetchObservable
                    .observeOn(publishScheduler)
                    .subscribe(new Action1<Model>() {
                        @Override
                        public void call(Model model) {
                            callback.onFetch(params, loadCriteria, model);
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            callback.onFetchError(params, loadCriteria, throwable);
                        }
                    });
            return subscription;
        }catch (CriteriaExpiredException e){
            LoadCriteria newCriteria = null;
            try {
                newCriteria = loadCriteria.next();
            } catch (NoMoreCriteriaException e1) {
                callback.onFetchError(params, loadCriteria, e1);
            }
            return fetch(params, newCriteria, callback);
        }
    }


    @Override
    public Subscription commit(final Model model, final StoreCriteria storeCriteria, final CommitCallback callback) {
        DataStore dataStore = dataStoreFactory.get(storeCriteria);
        Observable<Boolean> storeObservable = dataStore.commit(model);
        Subscription subscription = storeObservable
                .observeOn(publishScheduler)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean result) {
                        if (result) {
                            outdate.setLastUpdate(model);
                        }
                        callback.onCommit(storeCriteria, result);

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        callback.onCommitError(storeCriteria, throwable);
                    }
                });
        return subscription;
    }

    @Override
    public Subscription remove(final Params params, final RemoveCriteria removeCriteria, final RemoveCallback callback) {
        DataStore dataStore = dataStoreFactory.get(removeCriteria);
        Observable<Boolean> removeObservable = dataStore.remove(params);
        Subscription subscription = removeObservable
                .observeOn(publishScheduler)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean result) {
                        if (result){
                            outdate.remove(params);
                        }
                        callback.onRemove(removeCriteria, result);

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        callback.onRemoveError(removeCriteria, throwable);
                    }
                });
        return subscription;
    }

}
