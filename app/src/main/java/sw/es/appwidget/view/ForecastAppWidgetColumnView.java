package sw.es.appwidget.view;

import android.content.Context;
import android.widget.RemoteViews;

import sw.es.dagger2.R;
import sw.es.model.local.Forecast;
import sw.es.model.local.ForecastWeather;

/**
 * Created by alberto on 3/11/15.
 */
public class ForecastAppWidgetColumnView implements ForecastColumnView {


    private Context context;


    public ForecastAppWidgetColumnView(Context context) {
        this.context = context;
    }


    @Override
    public RemoteViews build(int column, Forecast forecast, ForecastWeather forecastWeather) {
        RemoteViews remote = new RemoteViews(context.getPackageName(), R.layout.app_widget_column);
        //TODO:
        remote.setTextViewText(R.id.appwidget_date, "Hora: " + String.valueOf(column));
        return remote;
    }


    @Override
    public int viewId(int column) {
        if (column == 0){
            return R.id.appwidget_first;
        }else if(column == 1){
            return R.id.appwidget_second;
        }else if (column == 2) {
            return R.id.appwidget_third;
        }else{
            return R.id.appwidget_fourth;
        }
    }
}
