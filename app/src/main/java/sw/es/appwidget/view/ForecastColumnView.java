package sw.es.appwidget.view;

import android.widget.RemoteViews;

import sw.es.model.local.Forecast;
import sw.es.model.local.ForecastWeather;

/**
 * Created by alberto on 3/11/15.
 */
public interface ForecastColumnView {
    RemoteViews build(int column, Forecast forecast, ForecastWeather forecastWeather);
    int viewId(int column);
}
