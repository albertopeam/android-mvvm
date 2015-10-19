package sw.es.repository.repository;

import javax.inject.Singleton;

import rx.Observable;
import sw.es.repository.criteria.FetchCriteria;
import sw.es.repository.criteria.StoreCriteria;
import sw.es.repository.datastore.AbsDataStoreFactory;
import sw.es.repository.interfaces.Outdate;
import sw.es.repository.interfaces.Repository;


/**
 * Created by albertopenasamor on 27/5/15.
 */
@Singleton
public abstract class AbstractRepository<Model> implements Repository<Model> {


    private AbsDataStoreFactory<Model> absDataStoreFactory;
    private Outdate<Model> outdate;


    //TODO: el outdate igual se sale de responsabilidad aquí, esta en el factory para ver quien debe buscar...


    public AbstractRepository(AbsDataStoreFactory absDataStoreFactory, Outdate<Model> outdate) {
        this.absDataStoreFactory = absDataStoreFactory;
        this.outdate = outdate;
    }

    //TODO: Este deberia guardar?
    //TODO: con los observables o guardo aquí o fuera en el interactor....mejor interactor(caso de uso)
    //TODO: debería implementar un isStored() o almaceno siempre???

    @Override
    public Observable<Model> findById(long id, FetchCriteria fetchCriteria) {
        //TODO:
        return null;
    }


    //Para subidas....
    @Override
    public Observable<Boolean> save(Model weather, StoreCriteria storeCriteria){
        //TODO:
        return null;
    }
}
