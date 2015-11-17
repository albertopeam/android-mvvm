package sw.es.domain.repository.repo.usecase;

import javax.inject.Inject;

import sw.es.domain.repository.repo.repository.LoadCallback;
import sw.es.domain.repository.repo.criteria.LoadCriteria;
import sw.es.domain.repository.repo.repository.Repository;

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
        repository.fetch(params, LoadCriteria.newGet(), new LoadCallback<Model, Params>() {
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
