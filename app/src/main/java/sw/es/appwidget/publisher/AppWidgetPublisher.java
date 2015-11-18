package sw.es.appwidget.publisher;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

/**
 * Created by albertopenasamor on 4/11/15.
 */
public class AppWidgetPublisher<T extends AppWidgetProvider> {


    private Context context;
    private Class<T>aClass;


    public AppWidgetPublisher(Context context, Class<T> t) {
        this.context = context;
        this.aClass = t;
    }


    public void update(int appWidgetId, RemoteViews remoteViews){
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(appWidgetId, remoteViews);
    }
}
