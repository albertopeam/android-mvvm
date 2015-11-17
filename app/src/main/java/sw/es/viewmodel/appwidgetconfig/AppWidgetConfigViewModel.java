package sw.es.viewmodel.appwidgetconfig;

import android.databinding.Bindable;

import java.util.List;

import javax.inject.Inject;

import sw.es.appwidget.AppWidgetPublisher;
import sw.es.appwidget.ForecastAppWidget;
import sw.es.appwidget.view.ForecastAppWidgetView;
import sw.es.dagger2.BR;
import sw.es.model.local.FavouriteLocation;
import sw.es.domain.sharedprefs.AppShared;
import sw.es.domain.sharedprefs.usecase.FetchFavCallback;
import sw.es.domain.sharedprefs.usecase.FetchFavLocationsUseCase;
import sw.es.viewmodel.abs.AbsViewModel;

/**
 * Created by albertopenasamor on 16/11/15.
 */
public class AppWidgetConfigViewModel extends AbsViewModel implements AbsAppWidgetConfigViewModel, FetchFavCallback{


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
        setLoading(true);
        fetchFavLocationsUseCase.run(this);
    }

    @Override
    public void configure(int appWidgetId, FavouriteLocation favouriteLocation) {
        appShared.put(String.valueOf(appWidgetId), favouriteLocation.getName());
        widgetPublisher.update(forecastAppWidgetView.setLoading());
        viewCallback.fetchForecast();
        viewCallback.close();
    }

    @Override
    public void onFavourites(List<FavouriteLocation> favouriteLocations) {
        setLoading(false);
        viewCallback.onLocations(favouriteLocations);
    }

    @Override
    public void onEmptyFavourites() {
        setLoading(false);
        setEmpty(true);
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

