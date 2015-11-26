package sw.es.domain.repository.repo.usecase;

import javax.inject.Inject;

import rx.Subscription;
import sw.es.dagger2.BuildConfig;
import sw.es.domain.repository.repo.criteria.LoadCriteria;
import sw.es.domain.repository.repo.criteria.StoreCriteria;
import sw.es.domain.repository.repo.exception.NoMoreCriteriaException;
import sw.es.domain.repository.repo.exception.NotFoundInDataStoreException;
import sw.es.domain.repository.repo.exception.NotStoredInRepositoryException;
import sw.es.domain.repository.repo.repository.CommitCallback;
import sw.es.domain.repository.repo.repository.LoadCallback;
import sw.es.domain.repository.repo.repository.Repository;

/**
 * Created by albertopenasamor on 21/10/15.
 */
public class PullUseCase<Model, Params> implements UseCase<Params>, LoadCallback<Model, Params>, CommitCallback{


    private Repository<Model, Params> repository;
    private UseCaseCallback callback;
    private Model fetchModel;
    private Params params;
    private Subscription subscription;


    @Inject
    public PullUseCase(Repository<Model, Params> repository) {
        this.repository = repository;
    }


    @Override
    public void run(Params params, final UseCaseCallback callback) {
        this.callback = callback;
        this.params = params;
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
        if (loadCriteria.isNewData()){
            fetchModel = model;
            subscription = repository.commit(model, StoreCriteria.newCommit(), this);
        }else{
            callback.onResult(params, model);
        }
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
        if (BuildConfig.DEBUG){
            throwable.printStackTrace();
        }
        callback.onResultError(params, throwable);
    }


    private boolean hasSubscription(){
        return subscription != null;
    }


    private boolean isSubscribed(){
        return !subscription.isUnsubscribed();
    }
}
