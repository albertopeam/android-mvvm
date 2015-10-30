package sw.es.model.repository.usecase;

import javax.inject.Inject;

import sw.es.model.repository.criteria.RemoveCriteria;
import sw.es.model.repository.repository.RemoveCallback;
import sw.es.model.repository.repository.Repository;

/**
 * Created by albertopenasamor on 21/10/15.
 */
public class RemoveUseCase<Model, Params> implements UseCase<Params>{


    private Repository<Model, Params> repository;


    @Inject
    public RemoveUseCase(Repository<Model, Params> repository) {
        this.repository = repository;
    }


    @Override
    public void run(final Params params, final UseCaseCallback callback) {
        repository.remove(params, RemoveCriteria.newCommit(), new RemoveCallback() {
            @Override
            public void onRemove(RemoveCriteria removeCriteria, Boolean result) {
                callback.onResult(params, result);
            }

            @Override
            public void onRemoveError(RemoveCriteria removeCriteria, Throwable throwable) {
                callback.onResultError(params, throwable);
            }
        });
    }

}
