package sw.es.model.repository.datastore;


import rx.Observable;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public interface DataStore<Model, Params> {
    Observable<Model> fetch(Params params);
    Observable<Boolean> commit(Model model);
}
