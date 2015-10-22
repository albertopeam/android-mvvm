package sw.es.model.repository.usecase;

/**
 * Created by albertopenasamor on 22/10/15.
 */
public interface UseCaseCallback<Params, Result> {
    void onResult(Params params, Result result);
    void onResultError(Params params, Throwable throwable);
}
