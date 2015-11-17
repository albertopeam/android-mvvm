package sw.es.domain.sharedprefs.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import sw.es.model.local.FavouriteLocation;
import sw.es.domain.rx.ObservableCreator;
import sw.es.domain.sharedprefs.AppShared;

import static android.util.Log.e;
import static sw.es.dagger2.BuildConfig.DEBUG;

/**
 * Created by albertopenasamor on 27/10/15.
 */
public class FetchFavouritesLocationsUseCase implements UseCase<FetchFavouritesCallback> {


    private static final String TAG = FetchFavouritesLocationsUseCase.class.getSimpleName();
    private AppShared appShared;
    private Scheduler executionScheduler;
    private Scheduler listenScheduler;
    private FetchFavouritesCallback fetchFavouritesCallback;


    @Inject
    public FetchFavouritesLocationsUseCase(AppShared appShared, Scheduler executionScheduler, Scheduler listenScheduler){
        this.appShared = appShared;
        this.executionScheduler = executionScheduler;
        this.listenScheduler = listenScheduler;
    }


    @Override
    public void run(final FetchFavouritesCallback fetchFavouritesCallback){
        this.fetchFavouritesCallback = fetchFavouritesCallback;

        ObservableCreator.create(new Callable<List<FavouriteLocation>>() {
            @Override
            public List<FavouriteLocation> call() throws Exception {
                List<String>list = appShared.getStringsFromSet(FavoriteLocationKeys.KEY_FAVOURITES);
                List<FavouriteLocation>favouriteLocationList = new ArrayList<>();
                for (String string:list){
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
                        fetchFavouritesCallback.onEmptyFavourite();
                    }

                    @Override
                    public void onNext(List<FavouriteLocation> favouriteLocations) {
                        if (favouriteLocations.isEmpty()){
                            fetchFavouritesCallback.onEmptyFavourite();
                        }else{
                            emitFavoriteLocations(favouriteLocations);
                        }
                    }
                });

    }


    private void emitFavoriteLocations(List<FavouriteLocation>favouriteLocations){
        Observable.from(favouriteLocations)
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(executionScheduler)
                .observeOn(listenScheduler)
                .subscribe(new Observer<FavouriteLocation>() {
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
                    }

                    @Override
                    public void onNext(FavouriteLocation favouriteLocation) {
                        fetchFavouritesCallback.onFavourite(favouriteLocation);
                    }
                });
    }
}
