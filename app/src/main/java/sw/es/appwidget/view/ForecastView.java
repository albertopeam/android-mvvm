package sw.es.appwidget.view;

import android.widget.RemoteViews;

import sw.es.model.local.Forecast;

/**
 * Created by albertopenasamor on 3/11/15.
 */
public interface ForecastView {
    RemoteViews setLoading();
    RemoteViews setForecast(Forecast forecast);
    RemoteViews setConfig();
    RemoteViews setError(Throwable throwable);
}
