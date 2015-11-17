package sw.es.domain.sharedprefs.usecase;

/**
 * Created by albertopenasamor on 27/10/15.
 */
public interface UseCase<Data> {
    void run(final Data data);
}
