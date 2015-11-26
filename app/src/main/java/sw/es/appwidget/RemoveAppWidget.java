package sw.es.appwidget;

import sw.es.domain.sharedprefs.AppShared;

/**
 * Created by alberto on 26/11/15.
 */
public class RemoveAppWidget {


    private static final String TAG = RemoveAppWidget.class.getSimpleName();
    private AppShared appShared;


    public RemoveAppWidget(AppShared appShared) {
        this.appShared = appShared;
    }


    public void run(int[]widgetIds){
        for (int i=0; widgetIds != null && i < widgetIds.length; i++){
            int key = widgetIds[i];
            appShared.remove(String.valueOf(key));
        }
    }
}
