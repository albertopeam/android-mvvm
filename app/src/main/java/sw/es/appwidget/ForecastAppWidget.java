package sw.es.appwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import javax.inject.Inject;

import sw.es.android.AndroidApp;
import sw.es.dagger2.BuildConfig;
import sw.es.di.component.DaggerForecastAppWidgetComponent;
import sw.es.di.component.ForecastAppWidgetComponent;
import sw.es.di.module.ForecastAppWidgetModule;

import static android.util.Log.e;
import static sw.es.dagger2.BuildConfig.DEBUG;

/**
 * Implementation of App Widget functionality.
 */
//TODO: gestionar llegada de multiples widgetIds - service. debe ser por lo que chocan
//TODO: no actualiza bien cada hora....
//TODO: quiza quitar el service y meter algún otro objeto que lo gestione mejor
//TODO: java.lang.IndexOutOfBoundsException: Inconsistency detected. Invalid item position 0(offset:1).state:1
public class ForecastAppWidget extends AppWidgetProvider {


    private static final String TAG = ForecastAppWidget.class.getSimpleName();
    @Inject RemoveAppWidget removeAppWidget;


    /**
     * This is called to update the App Widget at intervals defined by the updatePeriodMillis attribute in the AppWidgetProviderInfo
     * @param context
     * @param appWidgetManager
     * @param appWidgetIds
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        if (DEBUG) {
            e(TAG, "onUpdate");
        }
        printAppWidgetIds(appWidgetIds);
        refreshWidgets(context, appWidgetIds);
    }


    /**
     * Every call to the widget
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        initializeInjections(context);
        super.onReceive(context, intent);
    }


    /**
     * Widget removed
     * @param context
     * @param appWidgetIds
     */
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        removeAppWidget.run(appWidgetIds);
    }


    /**
     * Dagger injectionss
     * @param context
     */
    private void initializeInjections(Context context){
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "initializeInjections");
        }
        AndroidApp androidApp = (AndroidApp) context.getApplicationContext();
        ForecastAppWidgetComponent component = DaggerForecastAppWidgetComponent.builder()
                .applicationComponent(androidApp.getApplicationComponent())
                .forecastAppWidgetModule(new ForecastAppWidgetModule())
                .build();
        component.inject(this);
    }


    /**
     * Print the received appwidget ids
     */
    private void printAppWidgetIds(int[] appWidgetIds) {
        if (DEBUG) {
            for (int i=0;appWidgetIds != null && i<appWidgetIds.length;i++){
                e(TAG, "appWidgetId: " + appWidgetIds[i]);
            }
        }
    }


    /**
     * Print widget
     * @param context
     */
    private void refreshWidgets(Context context, int[]appWidgetIds){
        for (Integer appWidgetId:appWidgetIds){
            context.startService(ForecastAppWidgetService.newInstance(context, appWidgetId));
        }
    }
}