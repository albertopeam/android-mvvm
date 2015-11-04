package sw.es.appwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import javax.inject.Inject;

import sw.es.appwidget.view.ForecastAppWidgetView;
import sw.es.dagger2.BuildConfig;
import sw.es.di.component.DaggerForecastAppWidgetComponent;
import sw.es.di.component.ForecastAppWidgetComponent;
import sw.es.di.module.ForecastAppWidgetModule;

/**
 * Implementation of App Widget functionality.
 */
//TODO: gestionar diferentes widgets con diferentes locations.... un bucle for me da en onUpdate... y a ver como diferencio
//TODO: gestionar múltiples servicios... no se... por si hay más de un widget. Sinó tiro objetos normales....??? ni paja udea
public class ForecastAppWidget extends AppWidgetProvider {


    private static final String TAG = ForecastAppWidget.class.getSimpleName();
    @Inject ForecastAppWidgetView forecastAppWidgetView;


    /**
     * This is called to update the App Widget at intervals defined by the updatePeriodMillis attribute in the AppWidgetProviderInfo
     * @param context
     * @param appWidgetManager
     * @param appWidgetIds
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        printAppWidgetIds(appWidgetIds);
        refreshWidget(context);
    }


    @Override
    public void onEnabled(Context context) {}


    @Override
    public void onDisabled(Context context) {}


    /**
     * This is called for every broadcast and before each of the above callback methods.
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        initializeInjections(context);
    }


    /**
     * Dagger injectionss
     * @param context
     */
    private void initializeInjections(Context context){
        if (forecastAppWidgetView == null) {
            ForecastAppWidgetComponent component = DaggerForecastAppWidgetComponent.builder().forecastAppWidgetModule(new ForecastAppWidgetModule(context)).build();
            component.inject(this);
        }
    }


    /**
     * Print the received appwidget ids
     */
    private void printAppWidgetIds(int[] appWidgetIds) {
        if (BuildConfig.DEBUG) {
            for (int i=0;appWidgetIds != null && i<appWidgetIds.length;i++){
                Log.e(TAG, "appWidgetId: " + appWidgetIds[i]);
            }

        }
    }


    /**
     * Print widget
     * @param context
     */
    private void refreshWidget(Context context){
        context.startService(ForecastAppWidgetService.newInstance(context));
    }

}