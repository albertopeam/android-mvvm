package sw.es.repository.interfaces;


import rx.Observable;
import sw.es.repository.criteria.FetchCriteria;
import sw.es.repository.criteria.StoreCriteria;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public interface Repository<Model> {
    Observable<Model> findAll(FetchCriteria fetchCriteria);
    Observable<Model> findById(long id, FetchCriteria fetchCriteria);
    Observable<Boolean> save(Model model, StoreCriteria storeCriteria);
}
