package sw.es.domain.repository.repo.usecase;

import javax.inject.Inject;

import sw.es.domain.repository.repo.criteria.LoadCriteria;
import sw.es.domain.repository.repo.criteria.StoreCriteria;
import sw.es.domain.repository.repo.exception.NotStoredInRepositoryException;
import sw.es.domain.repository.repo.repository.CommitCallback;
import sw.es.domain.repository.repo.repository.LoadCallback;
import sw.es.domain.repository.repo.repository.Repository;

/**
 * Created by albertopenasamor on 21/10/15.
 */
//TODO: subscripciones al repo devolver?????
//TODO: para poder cancelar
public class PullUseCase<Model, Params> implements UseCase<Params>{


    private Repository<Model, Params> repository;


    @Inject
    public PullUseCase(Repository<Model, Params> repository) {
        this.repository = repository;
    }


    @Override
    public void run(Params params, final UseCaseCallback callback) {
        repository.fetch(params, LoadCriteria.newGet(), new LoadCallback<Model, Params>() {
            @Override
            public void onFetch(final Params params, LoadCriteria loadCriteria, Model model) {
                final Model fetchModel = model;
                if (loadCriteria.isNewData()){
                    repository.commit(model, StoreCriteria.newCommit(), new CommitCallback() {
                        @Override
                        public void onCommit(StoreCriteria storeCriteria, Boolean result) {
                            if (result){
                                callback.onResult(params, fetchModel);
                            }else{
                                callback.onResultError(params, new NotStoredInRepositoryException(fetchModel));
                            }
                        }

                        @Override
                        public void onCommitError(StoreCriteria storeCriteria, Throwable throwable) {
                            callback.onResultError(params, throwable);
                        }
                    });
                }else{
                    callback.onResult(params, model);
                }
            }

            @Override
            public void onFetchError(Params params, LoadCriteria loadCriteria, Throwable throwable) {
                callback.onResultError(params, throwable);
            }
        });
    }
}
