package sw.es.domain.sharedprefs.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import rx.Observer;
import rx.Scheduler;
import sw.es.dagger2.BuildConfig;
import sw.es.model.local.FavouriteLocation;
import sw.es.domain.rx.ObservableCreator;
import sw.es.domain.sharedprefs.AppShared;

import static android.util.Log.e;
import static sw.es.dagger2.BuildConfig.DEBUG;

/**
 * Created by albertopenasamor on 27/10/15.
 */
public class FetchFavLocationsUseCase implements UseCase<FetchFavCallback> {


    private static final String TAG = FetchFavLocationsUseCase.class.getSimpleName();
    private AppShared appShared;
    private Scheduler executionScheduler;
    private Scheduler listenScheduler;
    private FetchFavCallback fetchFavCallback;


    @Inject
    public FetchFavLocationsUseCase(AppShared appShared, Scheduler executionScheduler, Scheduler listenScheduler){
        this.appShared = appShared;
        this.executionScheduler = executionScheduler;
        this.listenScheduler = listenScheduler;
    }


    @Override
    public void run(final FetchFavCallback fetchFavCallback){
        this.fetchFavCallback = fetchFavCallback;

        ObservableCreator.create(new Callable<List<FavouriteLocation>>() {
            @Override
            public List<FavouriteLocation> call() throws Exception {
                List<String> list = appShared.getStringsFromSet(FavoriteLocationKeys.KEY_FAVOURITES);
                List<FavouriteLocation> favouriteLocationList = new ArrayList<>();
                for (String string : list) {
                    favouriteLocationList.add(new FavouriteLocation(string));
                }
                return favouriteLocationList;
            }
        })
                .subscribeOn(executionScheduler)
                .observeOn(listenScheduler)
                .subscribe(new Observer<List<FavouriteLocation>>() {
                    @Override
                    public void onCompleted() {
                        if (DEBUG) {
                            e(TAG, "onCompleted");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (DEBUG) {
                            e(TAG, "onError");
                            e.printStackTrace();
                        }
                        fetchFavCallback.onEmptyFavourites();
                    }

                    @Override
                    public void onNext(List<FavouriteLocation> favouriteLocations) {
                        if (BuildConfig.DEBUG) {
                            e(TAG, "onNext");
                        }
                        if (favouriteLocations.isEmpty()){
                            fetchFavCallback.onEmptyFavourites();
                        }else{
                            fetchFavCallback.onFavourites(favouriteLocations);
                        }
                    }
                });

    }
}
