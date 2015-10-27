package sw.es.viewmodel.weather;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import sw.es.model.local.FavouriteLocation;
import sw.es.model.local.Weather;
import sw.es.model.repository.usecase.UseCaseCallback;
import sw.es.model.repository.weather.usecase.WeatherPullUseCase;
import sw.es.model.usecase.FetchFavouritesCallback;
import sw.es.model.usecase.FetchFavouritesLocationsUseCase;
import sw.es.model.usecase.StoreFavouriteLocationUseCase;
import sw.es.viewmodel.abs.AbsViewModel;

/**
 * Created by alberto on 15/10/15.
 */
public class FavouriteWeathersViewModel extends AbsViewModel implements AbsFavouriteWeathersViewModel {


    private FavouriteWeathersListener favouriteWeathersListener;
    private WeatherPullUseCase weatherPullUseCase;
    private FetchFavouritesLocationsUseCase fetchFavouritesLocationsUseCase;
    private List<FavouriteLocation> favouriteLocationList;
    private StoreFavouriteLocationUseCase storeFavouriteLocationUseCase;
    private int loading = View.VISIBLE;


    @Inject
    public FavouriteWeathersViewModel(WeatherPullUseCase weatherPullUseCase, FetchFavouritesLocationsUseCase fetchFavouritesLocationsUseCase, StoreFavouriteLocationUseCase storeFavouriteLocationUseCase) {
        this.weatherPullUseCase = weatherPullUseCase;
        this.fetchFavouritesLocationsUseCase = fetchFavouritesLocationsUseCase;
        this.storeFavouriteLocationUseCase = storeFavouriteLocationUseCase;
        this.favouriteLocationList = new ArrayList<>();
    }


    @Override
    public void setup(FavouriteWeathersListener favouriteWeathersListener) {
        this.favouriteWeathersListener = favouriteWeathersListener;
    }


    @Override
    public void destroy() {
        //TODO: cancelar las posibles peticiones.... subscriptions.... booleanos....
        favouriteWeathersListener = null;
    }


    @Override
    public void load() {
        fetchFavouritesLocationsUseCase.run(new FetchFavouritesCallback() {
            @Override
            public void onFavourite(FavouriteLocation favouriteLocation) {
                favouriteLocationList.add(favouriteLocation);
                pull(favouriteLocation.getName());
            }
        });
    }


    @Override
    public void pull(String name) {
        if (hasNotFavouriteLocation(name)) {
            storeFavouriteLocationUseCase.run(name);

            weatherPullUseCase.run(name, new UseCaseCallback<String, Weather>() {
                @Override
                public void onResult(String s, Weather weather) {
                    if (hasView()) {
                        favouriteWeathersListener.onWeather(weather);
                    }
                }

                @Override
                public void onResultError(String s, Throwable throwable) {
                    if (hasView()) {
                        favouriteWeathersListener.onWeatherError(throwable);
                    }
                }
            });
        }else{
            favouriteWeathersListener.alreadyHasFavouriteLocation(new FavouriteLocation(name));
        }
    }


    private boolean hasView(){
        return favouriteWeathersListener != null;
    }


    private boolean hasNotFavouriteLocation(String name){
        FavouriteLocation newFavouriteLocation = new FavouriteLocation(name);
        for (FavouriteLocation favouriteLocation:favouriteLocationList){
            if (favouriteLocation.equals(newFavouriteLocation)){
                return true;
            }
        }
        return false;
    }
}
