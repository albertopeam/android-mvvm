package sw.es.appwidget.view;

import android.content.Context;
import android.view.View;
import android.widget.RemoteViews;

import java.util.List;

import javax.inject.Inject;

import sw.es.dagger2.R;
import sw.es.model.local.Forecast;
import sw.es.model.local.ForecastWeather;


/**
 * Created by albertopenasamor on 30/6/15.
 */
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
        return remoteView;
    }

    @Override
    public RemoteViews setForecast(Forecast forecast) {
        remoteView.setViewVisibility(R.id.appwidget_pbar, View.GONE);
        remoteView.setViewVisibility(R.id.appwidget_forecastlayout, View.VISIBLE);
        //TODO: fill with data

        //TODO: imagenes... recortar las imagenes de la lib meteocons
        //TODO: https://www.iconfinder.com/icons/110802/fog_sun_icon#size=128 o convertir a blanco o aplicar filtro, recoratar...

        List<ForecastWeather> forecastWeatherList = forecast.getForecastWeatherList();
        for(int i = 0; i < NUM_COLUMNS; i++) {
            if (forecastWeatherList.size() >= NUM_COLUMNS) {
                ForecastColumnView forecastColumnView = new ForecastAppWidgetColumnView(context);
                RemoteViews remoteViews = forecastColumnView.build(i, forecast, forecastWeatherList.get(i));
                int viewId = forecastColumnView.viewId(i);
                remoteView.addView(viewId, remoteViews);
            }else{
                //TODO: no creo que se de...
            }
        }


        return remoteView;
    }

    @Override
    public RemoteViews setError(Throwable throwable) {
        remoteView.setViewVisibility(R.id.appwidget_pbar, View.GONE);
        remoteView.setViewVisibility(R.id.appwidget_forecastlayout, View.GONE);
        //TODO: exception....

        return remoteView;
    }


/*
    public RemoteViews buildAppWidgetsError(Throwable error) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.current_weather_app_widget);

        if (error instanceof TimeoutException) {
            views.setTextViewText(R.id.error_tv, resources.getString(R.string.error_location_unavailable));
        } else if (error instanceof RetrofitError || error instanceof HttpException) {
            views.setTextViewText(R.id.error_tv, resources.getString(R.string.error_fetch_weather));
        } else {
            Log.e(TAG, error.getMessage());
            error.printStackTrace();
            throw new RuntimeException("See inner exception");
        }


        views.setViewVisibility(R.id.error_tv, View.VISIBLE);
        views.setViewVisibility(R.id.current_tv, View.GONE);
        views.setViewVisibility(R.id.max_tv, View.GONE);
        views.setViewVisibility(R.id.min_tv, View.GONE);
        views.setViewVisibility(R.id.location_tv, View.GONE);
        views.setViewVisibility(R.id.date_tv, View.GONE);
        views.setViewVisibility(R.id.forecast_tv, View.GONE);
        views.setViewVisibility(R.id.last_updated_at_tv, View.GONE);
        views.setViewVisibility(R.id.thermometer_iv, View.GONE);
        views.setViewVisibility(R.id.progress_bar, View.GONE);

        setEvents(views);
        return views;
    }


    public RemoteViews buildAppWidgetsUpdate(CurrentWeather currentWeather) {
        RemoteViews views = new RemoteViews(packageName, R.layout.current_weather_app_widget);

        views.setTextViewText(R.id.current_tv, TemperatureFormatter.format(currentWeather.getTemperature()));
        views.setTextViewText(R.id.max_tv, TemperatureFormatter.format(currentWeather.getMaximumTemperature()));
        views.setTextViewText(R.id.min_tv, TemperatureFormatter.format(currentWeather.getMinimumTemperature()));

        views.setTextViewText(R.id.location_tv, currentWeather.getLocationName());
        String date = new SimpleDateFormat("EE, d MMM yyyy").format(new Date(currentWeather.getTimestamp()*1000));
        views.setTextViewText(R.id.date_tv, date);
        views.setTextViewText(R.id.forecast_tv, currentWeather.getDescription());

        Date currentDate = new Date(System.currentTimeMillis());
        String currentDateString = new SimpleDateFormat("HH:mm:ss").format(currentDate);
        views.setTextViewText(R.id.last_updated_at_tv, String.format("%s %s", resources.getString(R.string.last_updated),currentDateString));

        views.setImageViewResource(R.id.thermometer_iv, R.drawable.thermometer);
        views.setViewVisibility(R.id.progress_bar, View.GONE);
        views.setViewVisibility(R.id.error_tv, View.GONE);
        views.setViewVisibility(R.id.current_tv, View.VISIBLE);
        views.setViewVisibility(R.id.max_tv, View.VISIBLE);
        views.setViewVisibility(R.id.min_tv, View.VISIBLE);
        views.setViewVisibility(R.id.location_tv, View.VISIBLE);
        views.setViewVisibility(R.id.date_tv, View.VISIBLE);
        views.setViewVisibility(R.id.forecast_tv, View.VISIBLE);
        views.setViewVisibility(R.id.last_updated_at_tv, View.VISIBLE);
        views.setViewVisibility(R.id.thermometer_iv, View.VISIBLE);

        setEvents(views);
        return views;
    }


    public RemoteViews buildAppWidgetsLoading() {
        RemoteViews views = new RemoteViews(packageName, R.layout.current_weather_app_widget);

        views.setViewVisibility(R.id.progress_bar, View.VISIBLE);

        views.setViewVisibility(R.id.error_tv, View.GONE);
        views.setViewVisibility(R.id.current_tv, View.GONE);
        views.setViewVisibility(R.id.max_tv, View.GONE);
        views.setViewVisibility(R.id.min_tv, View.GONE);
        views.setViewVisibility(R.id.location_tv, View.GONE);
        views.setViewVisibility(R.id.date_tv, View.GONE);
        views.setViewVisibility(R.id.forecast_tv, View.GONE);
        views.setViewVisibility(R.id.last_updated_at_tv, View.GONE);
        views.setViewVisibility(R.id.thermometer_iv, View.GONE);

        setEvents(views);
        return views;
    }


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
