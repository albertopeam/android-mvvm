package sw.es.appwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import javax.inject.Inject;

import sw.es.di.component.DaggerForecastAppWidgetComponent;
import sw.es.di.component.ForecastAppWidgetComponent;
import sw.es.di.module.ForecastAppWidgetModule;
import sw.es.model.local.Forecast;

/**
 * Implementation of App Widget functionality.
 */
public class ForecastAppWidget extends AppWidgetProvider {


    @Inject ForecastAppWidgetView forecastAppWidgetView;


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        //TODO: delete
        Forecast forecast = new Forecast();

        publishAppWidgetUpdate(context, forecastAppWidgetView.setLoading());
        publishAppWidgetUpdate(context, forecastAppWidgetView.setForecast(forecast));
        //context.startService(UpdateWeatherAppWidgetService.newInstance(context));
    }


    @Override
    public void onEnabled(Context context) {}


    @Override
    public void onDisabled(Context context) {}


    @Override
    public void onReceive(Context context, Intent intent) {
        initializeInjections(context);
        super.onReceive(context, intent);

        //TODO: custom action...
        if (ForecastAppWidgetView.RELOAD_ACTION.equals(intent.getAction())){
            /*
            RemoteViews remoteViews = forecastAppWidgetView.buildAppWidgetsLoading();
            publishAppWidgetUpdate(context, remoteViews);
            context.startService(UpdateWeatherAppWidgetService.newInstance(context));
            */
        }
    }


    private void initializeInjections(Context context){
        ForecastAppWidgetComponent component = DaggerForecastAppWidgetComponent.builder().forecastAppWidgetModule(new ForecastAppWidgetModule(context)).build();
        component.inject(this);
    }


    public static void publishAppWidgetUpdate(Context context, RemoteViews views){
        ComponentName thisWidget = new ComponentName(context, ForecastAppWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(thisWidget, views);
    }



}