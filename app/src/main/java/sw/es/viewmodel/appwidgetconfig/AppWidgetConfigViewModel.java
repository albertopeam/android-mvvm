package sw.es.viewmodel.appwidgetconfig;

import android.databinding.Bindable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import sw.es.appwidget.AppWidgetPublisher;
import sw.es.appwidget.ForecastAppWidget;
import sw.es.appwidget.view.ForecastAppWidgetView;
import sw.es.dagger2.BR;
import sw.es.dagger2.BuildConfig;
import sw.es.model.local.FavouriteLocation;
import sw.es.model.sharedprefs.AppShared;
import sw.es.model.sharedprefs.usecase.FetchFavLocationsUseCase;
import sw.es.viewmodel.abs.AbsViewModel;

/**
 * Created by albertopenasamor on 16/11/15.
 */
public class AppWidgetConfigViewModel extends AbsViewModel implements AbsAppWidgetConfigViewModel{


    //TODO; falta por meter y modificar este obj fetchFavLocationsUseCase!!!!
    private FetchFavLocationsUseCase fetchFavLocationsUseCase;
    private ForecastAppWidgetView forecastAppWidgetView;
    private AppWidgetPublisher<ForecastAppWidget> widgetPublisher;
    private AppShared appShared;
    private FavouriteLocationsCallback viewCallback;
    private boolean loading = false;
    private boolean empty = false;


    @Inject
    public AppWidgetConfigViewModel(FetchFavLocationsUseCase fetchFavLocationsUseCase, AppWidgetPublisher<ForecastAppWidget> appWidgetPublisher, AppShared appShared, ForecastAppWidgetView forecastAppWidgetView) {
        this.fetchFavLocationsUseCase = fetchFavLocationsUseCase;
        this.widgetPublisher = appWidgetPublisher;
        this.appShared = appShared;
        this.forecastAppWidgetView = forecastAppWidgetView;
    }

    @Override
    public void setup(FavouriteLocationsCallback callback) {
        this.viewCallback = callback;
    }


    @Override
    public void load() {
        if (BuildConfig.DEBUG){
            FavouriteLocation favouriteLocation = new FavouriteLocation("perillo");
            List<FavouriteLocation>favouriteLocationList = new ArrayList<>();
            favouriteLocationList.add(favouriteLocation);
            viewCallback.onLocations(favouriteLocationList);
        }else{
            //TODO; load from shared.... y reenviar a traves del callback
            throw new UnsupportedOperationException("Oh oh, not implemented yet!!!");
        }
    }

    @Override
    public void configure(int appWidgetId, FavouriteLocation favouriteLocation) {
        appShared.put(String.valueOf(appWidgetId), favouriteLocation.getName());
        widgetPublisher.update(forecastAppWidgetView.setLoading());
        viewCallback.fetchForecast();
        viewCallback.close();
    }

    @Override
    public void destroy() {
        viewCallback = null;
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

