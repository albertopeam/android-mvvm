package sw.es.viewmodel.appwidgetconfig;

import sw.es.model.local.FavouriteLocation;
import sw.es.viewmodel.abs.ViewModel;

/**
 * Created by albertopenasamor on 16/11/15.
 */
public interface AbsAppWidgetConfigViewModel extends ViewModel {
    void setup(FavouriteLocationsCallback callback);
    void load();
    void configure(int appWidgetId, FavouriteLocation favouriteLocation);
}
