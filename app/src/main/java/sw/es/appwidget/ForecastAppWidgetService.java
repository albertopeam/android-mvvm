package sw.es.appwidget;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;
import sw.es.appwidget.view.ForecastAppWidgetView;
import sw.es.dagger2.BuildConfig;
import sw.es.di.component.DaggerForecastAppWidgetComponent;
import sw.es.di.component.ForecastAppWidgetComponent;
import sw.es.di.module.ForecastAppWidgetModule;
import sw.es.model.local.Forecast;
import sw.es.model.repository.forecast.CloudForecastCityDataStore;

//TODO: repo: crear, probar e inyectar
public class ForecastAppWidgetService extends Service {

    private static final String TAG = ForecastAppWidgetService.class.getSimpleName();
    private CompositeSubscription mCompositeSubscription = null;


    @Inject ForecastAppWidgetView forecastAppWidgetView;
    @Inject AppWidgetPublisher<ForecastAppWidget>publisher;


    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, ForecastAppWidgetService.class);
        return intent;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjections(getApplicationContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (BuildConfig.DEBUG){
            Log.d(TAG, "onStartCommand");
        }
        if (!isRunning()){
            showLoading();
            fetchForecast();
        }else{
            if (BuildConfig.DEBUG){
                Log.d(TAG, "service already running");
            }
        }
        return Service.START_STICKY;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        /*if (!mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
        mCompositeSubscription = null;
        */
    }


    private void showLoading(){
        publisher.update(forecastAppWidgetView.setLoading());
    }

    private void fetchForecast(){
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "fetchWeatherData");
        }
        try {
            Thread.sleep(5000);
            Todo: impl
            CloudForecastCityDataStore
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Forecast forecast = new Forecast();
        publisher.update(forecastAppWidgetView.setForecast(forecast));
    }


    private void showError(){
        publisher.update(forecastAppWidgetView.setError(null));
    }


    private boolean isRunning(){
        return mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed();
    }

    /*

    private void updateAppWidgets(CurrentWeather currentWeather){
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "build app widget update");
        }
        RemoteViews updateViews = forecastAppWidgetView.buildAppWidgetsUpdate(currentWeather);
        CurrentWeatherAppWidget.publishAppWidgetUpdate(ForecastAppWidgetService.this, updateViews);
    }


    private void updateAppWidgetsWithError(Throwable error){
        RemoteViews updateViews = forecastAppWidgetView.buildAppWidgetsError(error);
        CurrentWeatherAppWidget.publishAppWidgetUpdate(ForecastAppWidgetService.this, updateViews);
    }
    */

    private void initializeInjections(Context context){
        ForecastAppWidgetComponent component = DaggerForecastAppWidgetComponent.builder().forecastAppWidgetModule(new ForecastAppWidgetModule(context)).build();
        component.inject(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}