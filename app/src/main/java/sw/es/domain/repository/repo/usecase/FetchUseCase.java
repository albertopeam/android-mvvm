package sw.es.domain.repository.repo.usecase;

import javax.inject.Inject;

import rx.Subscription;
import sw.es.dagger2.BuildConfig;
import sw.es.domain.repository.repo.exception.NoMoreCriteriaException;
import sw.es.domain.repository.repo.exception.NotFoundInDataStoreException;
import sw.es.domain.repository.repo.repository.LoadCallback;
import sw.es.domain.repository.repo.criteria.LoadCriteria;
import sw.es.domain.repository.repo.repository.Repository;

/**
 * Created by albertopenasamor on 21/10/15.
 */
public class FetchUseCase<Model, Params> implements UseCase<Params>, LoadCallback<Model, Params>{


    private Repository<Model, Params> repository;
    private UseCaseCallback callback;
    private Subscription subscription;


    @Inject
    public FetchUseCase(Repository<Model, Params> repository) {
        this.repository = repository;
    }


    @Override
    public void run(Params params, final UseCaseCallback callback) {
        this.callback = callback;
        subscription = repository.fetch(params, LoadCriteria.newGet(), this);
    }


    @Override
    public void cancel() {
        if (hasSubscription() && isSubscribed()){
            subscription.unsubscribe();
        }
    }


    @Override
    public void onFetch(Params params, LoadCriteria loadCriteria, Model model) {
        callback.onResult(params, model);
    }


    @Override
    public void onFetchError(Params params, LoadCriteria loadCriteria, Throwable throwable) {
        if (BuildConfig.DEBUG){
            throwable.printStackTrace();
        }
        if (throwable instanceof NotFoundInDataStoreException) {
            try {
                LoadCriteria newLoadCriteria = loadCriteria.next();
                subscription = repository.fetch(params, newLoadCriteria, this);
            } catch (NoMoreCriteriaException e) {
                callback.onResultError(params, throwable);
            }
        }else{
            callback.onResultError(params, throwable);
        }
    }


    private boolean hasSubscription(){
        return subscription != null;
    }


    private boolean isSubscribed(){
        return !subscription.isUnsubscribed();
    }
}
