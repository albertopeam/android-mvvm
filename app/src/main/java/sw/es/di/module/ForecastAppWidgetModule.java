package sw.es.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import sw.es.appwidget.AppWidgetPublisher;
import sw.es.appwidget.ForecastAppWidget;
import sw.es.appwidget.view.ForecastAppWidgetView;
import sw.es.appwidget.view.ForecastView;
import sw.es.di.common.PerService;

/**
 * Created by albertopenasamor on 2/11/15.
 */
@Module
public class ForecastAppWidgetModule {


    private Context context;


    public ForecastAppWidgetModule(Context context) {
        this.context = context;
    }


    @Provides
    @PerService
    ForecastView provideWeatherAppWidget(){
        return new ForecastAppWidgetView(context);
    }


    @Provides
    @PerService
    Context provideContext(){
        return context;
    }


    @Provides
    @PerService
    AppWidgetPublisher<ForecastAppWidget>providePublisher(){
        AppWidgetPublisher<ForecastAppWidget>publisher = new AppWidgetPublisher<>(context, ForecastAppWidget.class);
        return publisher;
    }
}
