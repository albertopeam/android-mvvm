package sw.es.appwidget.view;

import android.content.Context;
import android.view.View;
import android.widget.RemoteViews;

import sw.es.dagger2.R;
import sw.es.domain.mapper.IconMapper;
import sw.es.domain.mapper.StringMapper;
import sw.es.model.local.Forecast;
import sw.es.model.local.ForecastWeather;

/**
 * Created by alberto on 3/11/15.
 */
public class ForecastAppWidgetColumnView implements ForecastColumnView {


    private Context context;
    private IconMapper iconMapper;
    private StringMapper stringMapper;


    public ForecastAppWidgetColumnView(Context context, IconMapper iconMapper, StringMapper stringMapper) {
        this.context = context;
        this.iconMapper = iconMapper;
        this.stringMapper = stringMapper;

    }


    @Override
    public RemoteViews build(int column, Forecast forecast, ForecastWeather forecastWeather) {
        RemoteViews remote = new RemoteViews(context.getPackageName(), R.layout.app_widget_column);
        if (isFirstColumn(column)) {
            remote.setViewVisibility(R.id.appwidget_location, View.VISIBLE);
        }else{
            remote.setViewVisibility(R.id.appwidget_location, View.GONE);
        }
        remote.setImageViewResource(R.id.appwidget_icon, iconMapper.resourceFor(forecastWeather.getIcon()));
        remote.setTextViewText(R.id.appwidget_location, forecast.getName());
        remote.setTextViewText(R.id.appwidget_date, forecastWeather.getDatetimeFormatted());
        remote.setTextViewText(R.id.appwidget_description, stringMapper.traslationFor(forecastWeather.getDescription()));
        remote.setTextViewText(R.id.appwidget_humidity, forecastWeather.getHumidityPercent());
        remote.setTextViewText(R.id.appwidget_cloudiness, forecastWeather.getCloudinessPercent());
        remote.setTextViewText(R.id.appwidget_temp, forecastWeather.getTempInCelsiusC());
        remote.setTextViewText(R.id.appwidget_windspeed, forecastWeather.getWindSpeedInKM());
        remote.setTextViewText(R.id.appwidget_windspeeddegree, forecastWeather.getWindDegrees());
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

    private boolean isFirstColumn(int column){
        return viewId(column) == R.id.appwidget_first;
    }
}
