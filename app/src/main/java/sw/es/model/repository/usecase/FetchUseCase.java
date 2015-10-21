package sw.es.model.repository.usecase;

import javax.inject.Inject;

import sw.es.model.repository.repository.Repository;

/**
 * Created by albertopenasamor on 21/10/15.
 */
public class FetchUseCase<Model, Params> {


    private Repository<Model, Params> repository;


    @Inject
    public FetchUseCase(Repository<Model, Params> repository) {
        this.repository = repository;
    }

    conex directa,
    para el pull hay dos!!!
}
