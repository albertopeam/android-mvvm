package sw.es.domain.repository.forecast.usecase;

import android.util.Log;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import sw.es.dagger2.BuildConfig;
import sw.es.domain.repository.forecast.datastore.CloudForecastCityDataStore;
import sw.es.model.local.Forecast;

/**
 * Created by albertopenasamor on 17/11/15.
 */
public class ForecastFetchUseCase implements UseCase<String, Forecast> {


    private static final String TAG = ForecastFetchUseCase.class.getSimpleName();
    private CloudForecastCityDataStore cloudForecastCityDataStore;
    private Subscription subscription;


    @Inject
    public ForecastFetchUseCase(CloudForecastCityDataStore cloudForecastCityDataStore) {
        this.cloudForecastCityDataStore = cloudForecastCityDataStore;
    }


    @Override
    public void run(final String params, final UseCaseCallback<String, Forecast> callback) {
        subscription = cloudForecastCityDataStore.fetch(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Forecast>() {
                    @Override
                    public void onCompleted() {
                        if (BuildConfig.DEBUG) {
                            Log.e(TAG, "onCompleted");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (BuildConfig.DEBUG) {
                            Log.e(TAG, "onError");
                            if (e != null){
                                e.printStackTrace();
                            }
                        }
                        callback.onResultError(params, e);
                    }

                    @Override
                    public void onNext(Forecast forecast) {
                        if (BuildConfig.DEBUG) {
                            Log.e(TAG, "onNext");
                        }
                        callback.onResult(params, forecast);
                    }
                });
    }


    @Override
    public void cancel() {
        if (hasSubscription() && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }


    private boolean hasSubscription(){
        return subscription != null;
    }

}
