package sw.es.domain.repository.repo.usecase;

import javax.inject.Inject;

import rx.Subscription;
import sw.es.dagger2.BuildConfig;
import sw.es.domain.repository.repo.criteria.RemoveCriteria;
import sw.es.domain.repository.repo.repository.RemoveCallback;
import sw.es.domain.repository.repo.repository.Repository;

/**
 * Created by albertopenasamor on 21/10/15.
 */
public class RemoveUseCase<Model, Params> implements UseCase<Params>{


    private Repository<Model, Params> repository;
    private Subscription subscription;


    @Inject
    public RemoveUseCase(Repository<Model, Params> repository) {
        this.repository = repository;
    }


    @Override
    public void run(final Params params, final UseCaseCallback callback) {
        subscription = repository.remove(params, RemoveCriteria.newCommit(), new RemoveCallback() {
            @Override
            public void onRemove(RemoveCriteria removeCriteria, Boolean result) {
                callback.onResult(params, result);
            }

            @Override
            public void onRemoveError(RemoveCriteria removeCriteria, Throwable throwable) {
                if (BuildConfig.DEBUG){
                    throwable.printStackTrace();
                }
                callback.onResultError(params, throwable);
            }
        });
    }

    @Override
    public void cancel() {
        if (hasSubscription() && isSubscribed()){
            subscription.unsubscribe();
        }
    }


    private boolean hasSubscription(){
        return subscription != null;
    }


    private boolean isSubscribed(){
        return !subscription.isUnsubscribed();
    }

}
