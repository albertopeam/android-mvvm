package sw.es.repository.interfaces;


import rx.Observable;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public interface DataStore<Model> {
    Observable<Model> find(long id);
    Observable<Boolean> save(Model model);
}
