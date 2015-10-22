package sw.es.model.repository.usecase;

import javax.inject.Inject;

import sw.es.model.repository.repository.FetchCallback;
import sw.es.model.repository.criteria.LoadCriteria;
import sw.es.model.repository.repository.Repository;

/**
 * Created by albertopenasamor on 21/10/15.
 */
public class FetchUseCase<Model, Params> implements UseCase<Params>{


    private Repository<Model, Params> repository;


    @Inject
    public FetchUseCase(Repository<Model, Params> repository) {
        this.repository = repository;
    }


    @Override
    public void run(Params params, final UseCaseCallback callback) {
        repository.fetch(params, LoadCriteria.newFetch(), new FetchCallback<Model, Params>() {
            @Override
            public void onFetch(Params params, LoadCriteria loadCriteria, Model model) {
                callback.onResult(params, model);
            }

            @Override
            public void onFetchError(Params params, LoadCriteria loadCriteria, Throwable throwable) {
                callback.onResultError(params, throwable);
            }
        });
    }
}
