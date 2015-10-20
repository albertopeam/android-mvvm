package sw.es.model.repository.repository;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import sw.es.model.repository.callback.FetchCallback;
import sw.es.model.repository.criteria.FetchCriteria;
import sw.es.model.repository.criteria.StoreCriteria;
import sw.es.model.repository.datastore.AbsDataStoreFactory;
import sw.es.model.repository.datastore.DataStore;


/**
 * Created by albertopenasamor on 27/5/15.
 */
public class AbstractRepository<Model, Params> implements Repository<Model, Params> {


    private AbsDataStoreFactory<Model> absDataStoreFactory;
    private Scheduler scheduler;


    public AbstractRepository(AbsDataStoreFactory<Model> absDataStoreFactory, Scheduler scheduler) {
        this.absDataStoreFactory = absDataStoreFactory;
        this.scheduler = scheduler;
    }

    //TODO: LIKE SOURCE TREE!!!! is a repo
    JODIDO LOS OBSERVABLES!!!!
    todo: no tiene sentido devolver observable + callback

    @Override
    public Observable<Model> fetch(Params params, FetchCriteria fetchCriteria, final FetchCallback<Model, Params> callback) {
        DataStore weatherDataStore = absDataStoreFactory.get(fetchCriteria);
        Observable<Model> modelObservable = weatherDataStore.fetch(params);
        modelObservable.subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        //TODO: null => next criteria, dejar tirar con null, la idea es hacerlo recursivo y si da weatherDataStore == null => devolver al callback un vacio

        //TODO: error => callback error
    }

    @Override
    public Observable<Model> pull(Params params, FetchCriteria fetchCriteria) {
        return null;
    }

    @Override
    public Observable<Boolean> commit(Model model, StoreCriteria storeCriteria) {
        return null;
    }

    @Override
    public Observable<Boolean> push(Model model, StoreCriteria storeCriteria) {
        return null;
    }


}
