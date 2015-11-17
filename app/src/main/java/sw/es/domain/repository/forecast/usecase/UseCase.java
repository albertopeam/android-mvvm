package sw.es.domain.repository.forecast.usecase;

public interface UseCase<Params, Result> {
    void run(final Params params, final UseCaseCallback<Params, Result> callback);
    void cancel();
}