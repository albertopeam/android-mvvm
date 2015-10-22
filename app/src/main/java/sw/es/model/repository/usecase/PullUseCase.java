package sw.es.model.repository.usecase;

import javax.inject.Inject;

import sw.es.model.repository.repository.Repository;

/**
 * Created by albertopenasamor on 21/10/15.
 */
public class PullUseCase<Model, Params> implements UseCase<Params>{


    private Repository<Model, Params> repository;


    @Inject
    public PullUseCase(Repository<Model, Params> repository) {
        this.repository = repository;
    }


    @Override
    public void run(Params params, final UseCaseCallback callback) {
        repository.fetch(params, Fet);
    }
}
