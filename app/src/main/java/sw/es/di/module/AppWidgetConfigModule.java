package sw.es.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import sw.es.appwidget.publisher.AppWidgetPublisher;
import sw.es.appwidget.ForecastAppWidget;
import sw.es.appwidget.view.ForecastAppWidgetView;
import sw.es.di.common.ExecutionScheduler;
import sw.es.di.common.ForApplication;
import sw.es.di.common.ListenScheduler;
import sw.es.di.common.PerActivity;
import sw.es.domain.sharedprefs.AppShared;
import sw.es.domain.sharedprefs.usecase.FetchFavLocationsUseCase;
import sw.es.viewmodel.appwidgetconfig.AppWidgetConfigViewModel;

/**
 * Created by albertopenasamor on 17/11/15.
 */
@Module
public class AppWidgetConfigModule {


    @Provides
    @PerActivity
    AppWidgetConfigViewModel provideAppWidgetConfigViewModel(FetchFavLocationsUseCase fetchFavLocationsUseCase, AppWidgetPublisher<ForecastAppWidget> appWidgetPublisher, AppShared appShared, ForecastAppWidgetView forecastAppWidgetView){
        return new AppWidgetConfigViewModel(fetchFavLocationsUseCase, appWidgetPublisher, appShared, forecastAppWidgetView);
    }

    @Provides
    @PerActivity
    FetchFavLocationsUseCase provideFetchFavLocationsUseCase(AppShared appShared, @ExecutionScheduler Scheduler executioScheduler, @ListenScheduler Scheduler listenScheduler){
        return new FetchFavLocationsUseCase(appShared, executioScheduler, listenScheduler);
    }

    @Provides
    @PerActivity
    AppWidgetPublisher<ForecastAppWidget> provideWidgetAppWidgetPublisher(@ForApplication Context context){
        AppWidgetPublisher<ForecastAppWidget>publisher = new AppWidgetPublisher<>(context, ForecastAppWidget.class);
        return publisher;
    }

    @Provides
    @PerActivity
    ForecastAppWidgetView provideForecastAppWidgetView(@ForApplication Context context){
        return new ForecastAppWidgetView(context);
    }
}
