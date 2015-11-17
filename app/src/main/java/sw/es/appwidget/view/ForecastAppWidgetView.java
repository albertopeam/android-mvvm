package sw.es.appwidget.view;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.RemoteViews;

import java.util.List;

import javax.inject.Inject;

import retrofit.HttpException;
import sw.es.dagger2.R;
import sw.es.model.local.Forecast;
import sw.es.model.local.ForecastWeather;


/**
 * Created by albertopenasamor on 30/6/15.
 */
//TODO: add error cases.... segun aparezcan
//TODO: boton si devuelve una castaña para que recarge!!! y de donde saco el id del widget, its magic!!!
public class ForecastAppWidgetView implements ForecastView{


    private static final String TAG = ForecastAppWidgetView.class.getSimpleName();
    public static final String RELOAD_ACTION = "reload_btn";
    private static int NUM_COLUMNS = 4;
    private Context context;
    private RemoteViews remoteView;


    @Inject
    public ForecastAppWidgetView(Context context) {
        this.context = context;
        this.remoteView = new RemoteViews(context.getPackageName(), R.layout.app_widget);
    }

    @Override
    public RemoteViews setLoading() {
        remoteView.setViewVisibility(R.id.appwidget_pbar, View.VISIBLE);
        remoteView.setViewVisibility(R.id.appwidget_forecastlayout, View.GONE);
        remoteView.setViewVisibility(R.id.appwidget_errortv, View.GONE);
        remoteView.setViewVisibility(R.id.appwidget_configtv, View.GONE);
        return remoteView;
    }

    @Override
    public RemoteViews setForecast(Forecast forecast) {
        remoteView.setViewVisibility(R.id.appwidget_pbar, View.GONE);
        remoteView.setViewVisibility(R.id.appwidget_errortv, View.GONE);
        remoteView.setViewVisibility(R.id.appwidget_configtv, View.GONE);
        remoteView.setViewVisibility(R.id.appwidget_forecastlayout, View.VISIBLE);

        List<ForecastWeather> forecastWeatherList = forecast.getForecastWeatherList();
        for(int i = 0; i < NUM_COLUMNS; i++) {
            ForecastColumnView forecastColumnView = new ForecastAppWidgetColumnView(context);
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
        remoteView.setViewVisibility(R.id.appwidget_errortv, View.GONE);
        remoteView.setViewVisibility(R.id.appwidget_configtv, View.VISIBLE);
        return remoteView;
    }

    @Override
    public RemoteViews setError(Throwable throwable) {
        remoteView.setViewVisibility(R.id.appwidget_pbar, View.GONE);
        remoteView.setViewVisibility(R.id.appwidget_errortv, View.VISIBLE);
        remoteView.setViewVisibility(R.id.appwidget_forecastlayout, View.GONE);
        remoteView.setViewVisibility(R.id.appwidget_configtv, View.GONE);

        if (throwable instanceof HttpException){
            remoteView.setTextViewText(R.id.appwidget_errortv, getRes().getString(R.string.net_error));
        }else{
            remoteView.setTextViewText(R.id.appwidget_errortv, getRes().getString(R.string.undefined_error));
        }

        return remoteView;
    }


    private Resources getRes(){
        return context.getResources();
    }

    /*
    private void setEvents(RemoteViews views){
        views.setOnClickPendingIntent(R.id.widget_ll, makePendingIntent());
        views.setOnClickPendingIntent(R.id.reload_btn, getPendingSelfIntent(RELOAD_ACTION));
    }

    private PendingIntent makePendingIntent(){
        Intent intent = new Intent(context, CurrentLocationWeatherActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        return pendingIntent;
    }

    private PendingIntent getPendingSelfIntent(String action) {
        Intent intent = new Intent(context, CurrentWeatherAppWidget.class);
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
    */
}
