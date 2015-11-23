package sw.es.appwidget.view;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;

import retrofit.HttpException;
import sw.es.appwidget.ForecastAppWidget;
import sw.es.dagger2.BuildConfig;
import sw.es.dagger2.R;
import sw.es.domain.mapper.IconMapper;
import sw.es.domain.mapper.StringMapper;
import sw.es.model.local.Forecast;
import sw.es.model.local.ForecastWeather;


/**
 * Created by albertopenasamor on 30/6/15.
 */
//TODO: add error cases.... segun aparezcan
public class ForecastAppWidgetView implements ForecastView{


    private static final String TAG = ForecastAppWidgetView.class.getSimpleName();
    private static int NUM_COLUMNS = 4;
    private Context context;
    private RemoteViews remoteView;
    private IconMapper iconMapper;
    private StringMapper stringMapper;


    @Inject
    public ForecastAppWidgetView(Context context, IconMapper iconMapper, StringMapper stringMapper) {
        this.context = context;
        this.iconMapper = iconMapper;
        this.stringMapper = stringMapper;
        this.remoteView = new RemoteViews(context.getPackageName(), R.layout.app_widget);
    }


    @Override
    public RemoteViews setLoading() {
        remoteView.setViewVisibility(R.id.appwidget_pbar, View.VISIBLE);
        remoteView.setViewVisibility(R.id.appwidget_forecastlayout, View.GONE);
        remoteView.setViewVisibility(R.id.appwidget_errorview, View.GONE);
        remoteView.setViewVisibility(R.id.appwidget_configtv, View.GONE);
        return remoteView;
    }


    @Override
    public RemoteViews setForecast(Forecast forecast) {
        remoteView.setViewVisibility(R.id.appwidget_pbar, View.GONE);
        remoteView.setViewVisibility(R.id.appwidget_errorview, View.GONE);
        remoteView.setViewVisibility(R.id.appwidget_configtv, View.GONE);
        remoteView.setViewVisibility(R.id.appwidget_forecastlayout, View.VISIBLE);

        List<ForecastWeather> forecastWeatherList = forecast.getForecastWeatherList();
        for(int i = 0; i < NUM_COLUMNS; i++) {
            ForecastColumnView forecastColumnView = new ForecastAppWidgetColumnView(context, iconMapper, stringMapper);
            RemoteViews remoteViews = forecastColumnView.build(i, forecast, forecastWeatherList.get(i));
            int viewId = forecastColumnView.viewId(i);
            remoteView.addView(viewId, remoteViews);
        }

        return remoteView;
    }

    @Override
    public RemoteViews setConfig() {
        remoteView.setViewVisibility(R.id.appwidget_pbar, View.GONE);
        remoteView.setViewVisibility(R.id.appwidget_forecastlayout, View.GONE);
        remoteView.setViewVisibility(R.id.appwidget_errorview, View.GONE);
        remoteView.setViewVisibility(R.id.appwidget_configtv, View.VISIBLE);
        return remoteView;
    }


    @Override
    public RemoteViews setError(int appWidgetId, Throwable throwable) {
        remoteView.setViewVisibility(R.id.appwidget_pbar, View.GONE);
        remoteView.setViewVisibility(R.id.appwidget_errorview, View.VISIBLE);
        remoteView.setViewVisibility(R.id.appwidget_forecastlayout, View.GONE);
        remoteView.setViewVisibility(R.id.appwidget_configtv, View.GONE);

        if (throwable instanceof UnknownHostException){
            remoteView.setTextViewText(R.id.appwidget_errortv, getRes().getString(R.string.no_net_error));
        } else if (throwable instanceof HttpException){
            remoteView.setTextViewText(R.id.appwidget_errortv, getRes().getString(R.string.net_error));
        }else{
            remoteView.setTextViewText(R.id.appwidget_errortv, getRes().getString(R.string.undefined_error));
        }

        setEvents(appWidgetId, remoteView);
        return remoteView;
    }


    private Resources getRes(){
        return context.getResources();
    }


    private void setEvents(int appWidgetId, RemoteViews views){
        views.setOnClickPendingIntent(R.id.appwidget_reloadbtn, getPendingIntentUpdate(appWidgetId));
    }


    private PendingIntent getPendingIntentUpdate(int appWidgetId) {
        Intent intent = new Intent(context, ForecastAppWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int[] ids = {appWidgetId};
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "getPendingIntentUpdate widgetId: " + appWidgetId);
        }
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        return PendingIntent.getBroadcast(context, appWidgetId, intent, 0);
    }
}
