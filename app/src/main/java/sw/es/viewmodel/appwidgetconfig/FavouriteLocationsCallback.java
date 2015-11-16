package sw.es.viewmodel.appwidgetconfig;

import java.util.List;

import sw.es.model.local.FavouriteLocation;

public interface FavouriteLocationsCallback {
    void onLocations(List<FavouriteLocation> favouriteLocationList);
    void onLocationsError(Throwable throwable);
    void close();
    void fetchForecast();
}