package sw.es.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import sw.es.appwidget.AppWidgetPublisher;
import sw.es.appwidget.ForecastAppWidget;
import sw.es.appwidget.view.ForecastAppWidgetView;
import sw.es.appwidget.view.ForecastView;
import sw.es.di.common.ListenScheduler;
import sw.es.di.common.PerService;
import sw.es.model.repository.forecast.CloudForecastCityDataStore;
import sw.es.model.sharedprefs.AppShared;
import sw.es.model.sharedprefs.SharedPrefs;
import sw.es.network.WeatherBackendAPI;

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

    @Provides
    @PerService
    AppShared provideAppShared(){
        return new SharedPrefs(context);
    }


    @Provides
    @PerService
    CloudForecastCityDataStore provideCloudForecastCityDataStore(WeatherBackendAPI weatherBackendAPI, @ListenScheduler Scheduler scheduler){
        return new CloudForecastCityDataStore(weatherBackendAPI, scheduler);
    }
}
