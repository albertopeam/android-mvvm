package sw.es.model.repository.repo.usecase;

/**
 * Created by albertopenasamor on 22/10/15.
 */
public interface UseCase<Params> {
    void run(Params params, UseCaseCallback callback);
}
