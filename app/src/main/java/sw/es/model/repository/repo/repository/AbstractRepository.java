package sw.es.model.repository.repo.repository;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Action1;
import sw.es.model.repository.repo.criteria.LoadCriteria;
import sw.es.model.repository.repo.criteria.RemoveCriteria;
import sw.es.model.repository.repo.criteria.StoreCriteria;
import sw.es.model.repository.repo.datastore.DataStore;
import sw.es.model.repository.repo.datastore.DataStoreFactory;
import sw.es.model.repository.repo.exception.CriteriaExpiredException;
import sw.es.model.repository.repo.exception.NoMoreCriteriaException;
import sw.es.model.repository.repo.exception.NotFoundInDataStoreException;
import sw.es.model.repository.repo.outdate.Outdate;


/**
 * Created by albertopenasamor on 22/10/15.
 */
//TODO: gestionar la parada de los observables....Subscription.... OPCION: que lo gestione el caso de uso, y así se cancela desde afuera. sin guardar listas ni nada aquí dentro... QUIZA LA MEJOR
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
    public void fetch(final Params params, final LoadCriteria loadCriteria, final LoadCallback<Model, Params> callback) {
        try {
            DataStore dataStore = dataStoreFactory.get(loadCriteria, params);
            Observable<Model> fetchObservable = dataStore.fetch(params);
            fetchObservable
                    .observeOn(publishScheduler)
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
        }catch (CriteriaExpiredException e){
            LoadCriteria newCriteria = null;
            try {
                newCriteria = loadCriteria.next();
            } catch (NoMoreCriteriaException e1) {
                callback.onFetchError(params, loadCriteria, e1);
            }
            fetch(params, newCriteria, callback);
        }
    }


    @Override
    public void commit(final Model model, final StoreCriteria storeCriteria, final CommitCallback callback) {
        DataStore dataStore = dataStoreFactory.get(storeCriteria);
        Observable<Boolean> storeObservable = dataStore.commit(model);
        storeObservable
                .observeOn(publishScheduler)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean result) {
                        if (result){
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
    }

    @Override
    public void remove(final Params params, final RemoveCriteria removeCriteria, final RemoveCallback callback) {
        DataStore dataStore = dataStoreFactory.get(removeCriteria);
        Observable<Boolean> storeObservable = dataStore.remove(params);
        storeObservable
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
    }

}
