package sw.es.viewmodel.weather;

import android.databinding.Bindable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import sw.es.dagger2.BR;
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
    private boolean loading = false;
    private boolean empty = false;



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
        setLoading(true);
        fetchFavouritesLocationsUseCase.run(new FetchFavouritesCallback() {
            @Override
            public void onFavourite(FavouriteLocation favouriteLocation) {
                favouriteLocationList.add(favouriteLocation);
                pullWeather(favouriteLocation.getName());
            }

            @Override
            public void onEmptyFavourite() {
                setLoading(false);
                setEmpty(true);
            }
        });
    }


    @Override
    public void pull(String name) {
        if (hasNotFavouriteLocation(name)) {
            setLoading(true);
            storeFavoriteLocation(name);
            pullWeather(name);
        }else{
            favouriteWeathersListener.alreadyHasFavouriteLocation(new FavouriteLocation(name));
        }
    }


    private void pullWeather(String name){
        weatherPullUseCase.run(name, new UseCaseCallback<String, Weather>() {
            @Override
            public void onResult(String s, Weather weather) {
                if (hasView()) {
                    setEmpty(false);
                    setLoading(false);
                    favouriteWeathersListener.onWeather(weather);
                }
            }

            @Override
            public void onResultError(String s, Throwable throwable) {
                if (hasView()) {
                    setLoading(false);
                    favouriteWeathersListener.onWeatherError(throwable);
                }
            }
        });
    }


    private void storeFavoriteLocation(String name){
        FavouriteLocation favouriteLocation = new FavouriteLocation(name);
        favouriteLocationList.add(favouriteLocation);
        storeFavouriteLocationUseCase.run(name);
    }


    private boolean hasView(){
        return favouriteWeathersListener != null;
    }


    private boolean hasNotFavouriteLocation(String name){
        FavouriteLocation newFavouriteLocation = new FavouriteLocation(name);
        for (FavouriteLocation favouriteLocation:favouriteLocationList){
            if (favouriteLocation.equals(newFavouriteLocation)){
                return false;
            }
        }
        return true;
    }


    @Bindable
    public boolean isLoading() {
        return loading;
    }

    private void setLoading(boolean loading){
        this.loading = loading;
        notifyPropertyChanged(BR.loading);
    }

    @Bindable
    public boolean isEmpty() {
        return empty;
    }


    public void setEmpty(boolean empty) {
        this.empty = empty;
        notifyPropertyChanged(BR.empty);
    }
}
