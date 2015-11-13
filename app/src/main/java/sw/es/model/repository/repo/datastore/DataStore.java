package sw.es.model.repository.repo.datastore;


import rx.Observable;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public interface DataStore<Model, Params> {
    Observable<Model> fetch(final Params params);
    Observable<Boolean> commit(final Model model);
    Observable<Boolean> remove(final Params params);
}
