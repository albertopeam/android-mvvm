package sw.es.viewmodel.weather;

import android.databinding.Bindable;

import javax.inject.Inject;

import sw.es.dagger2.BR;
import sw.es.model.local.FavouriteLocation;
import sw.es.model.local.Weather;
import sw.es.model.local.FavouriteLocations;
import sw.es.domain.repository.repo.usecase.UseCaseCallback;
import sw.es.domain.repository.weather.usecase.WeatherPullUseCase;
import sw.es.domain.repository.weather.usecase.WeatherRemoveUseCase;
import sw.es.domain.sharedprefs.usecase.FetchFavouritesCallback;
import sw.es.domain.sharedprefs.usecase.FetchFavouritesLocationsUseCase;
import sw.es.domain.sharedprefs.usecase.RemoveFavouriteLocationUseCase;
import sw.es.domain.sharedprefs.usecase.StoreFavouriteLocationUseCase;
import sw.es.viewmodel.abs.AbsViewModel;

/**
 * Created by alberto on 15/10/15.
 */
public class FavouriteWeathersViewModel extends AbsViewModel implements AbsFavouriteWeathersViewModel {


    private FavouriteWeathersCallback viewCallback;//view callback
    private WeatherPullUseCase weatherPullUseCase;//repo pull case
    private WeatherRemoveUseCase weatherRemoveUseCase;//repo remove case
    private FetchFavouritesLocationsUseCase fetchFavouritesLocationsUseCase;//shared fetch locations
    private StoreFavouriteLocationUseCase storeFavouriteLocationUseCase;//shared store locations
    private RemoveFavouriteLocationUseCase removeFavouriteLocationUseCase;
    private FavouriteLocations favouriteLocations;//mem fav locations
    private boolean loading = false;
    private boolean empty = false;



    @Inject
    public FavouriteWeathersViewModel(WeatherPullUseCase weatherPullUseCase, WeatherRemoveUseCase weatherRemoveUseCase, FetchFavouritesLocationsUseCase fetchFavouritesLocationsUseCase, StoreFavouriteLocationUseCase storeFavouriteLocationUseCase, RemoveFavouriteLocationUseCase removeFavouriteLocationUseCase) {
        this.weatherPullUseCase = weatherPullUseCase;
        this.weatherRemoveUseCase = weatherRemoveUseCase;
        this.fetchFavouritesLocationsUseCase = fetchFavouritesLocationsUseCase;
        this.storeFavouriteLocationUseCase = storeFavouriteLocationUseCase;
        this.removeFavouriteLocationUseCase = removeFavouriteLocationUseCase;
        this.favouriteLocations = new FavouriteLocations();
    }


    @Override
    public void setup(FavouriteWeathersCallback favouriteWeathersCallback) {
        this.viewCallback = favouriteWeathersCallback;
    }


    @Override
    public void destroy() {
        //TODO: cancelar las posibles peticiones.... subscriptions.... booleanos....
        //TODO: isRunning
        viewCallback = null;
    }


    @Override
    public void load() {
        setLoading(true);
        fetchFavouritesLocationsUseCase.run(new FetchFavouritesCallback() {
            @Override
            public void onFavourite(FavouriteLocation favouriteLocation) {
                favouriteLocations.add(favouriteLocation);
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
        if (favouriteLocations.hasNot(name)) {
            setLoading(true);
            pullWeather(name);
        }else{
            viewCallback.alreadyHasFavouriteLocation(new FavouriteLocation(name));
        }
    }

    @Override
    public void remove(String name) {
        setLoading(true);
        favouriteLocations.remove(name);
        removeFavouriteLocationUseCase.run(name);
        weatherRemoveUseCase.run(name, new UseCaseCallback<String, Boolean>() {
            @Override
            public void onResult(String s, Boolean aBoolean) {
                setLoading(false);
            }

            @Override
            public void onResultError(String s, Throwable throwable) {
                setLoading(false);
                viewCallback.onRemoveError(throwable);
            }
        });
    }


    private void pullWeather(String name){
        weatherPullUseCase.run(name, new UseCaseCallback<String, Weather>() {
            @Override
            public void onResult(String s, Weather weather) {
                if (hasView()) {
                    setEmpty(false);
                    setLoading(false);
                    String key = weather.getName();
                    favouriteLocations.add(key);
                    storeFavouriteLocationUseCase.run(key);
                    viewCallback.onWeather(weather);
                }
            }

            @Override
            public void onResultError(String s, Throwable throwable) {
                if (hasView()) {
                    setLoading(false);
                    viewCallback.onWeatherError(throwable);
                }
            }
        });
    }


    private boolean hasView(){
        return viewCallback != null;
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
