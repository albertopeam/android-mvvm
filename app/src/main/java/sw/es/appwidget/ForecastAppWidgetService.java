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
import sw.es.model.sharedprefs.AppShared;

//TODO: repo: crear, probar e inyectar
public class ForecastAppWidgetService extends Service {


    private static final String TAG = ForecastAppWidgetService.class.getSimpleName();
    private CompositeSubscription mCompositeSubscription = null;


    @Inject ForecastAppWidgetView forecastAppWidgetView;
    @Inject AppWidgetPublisher<ForecastAppWidget>publisher;
    @Inject CloudForecastCityDataStore cloudForecastCityDataStore;
    @Inject AppShared appShared;


    public static Intent newInstance(Context context, int appWidgetId) {
        Intent intent = new Intent(context, ForecastAppWidgetService.class);
        intent.putExtra("appWidgetId", appWidgetId);
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
            fetchForecast(getAppWidgetId(intent));
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


    private int getAppWidgetId(Intent intent){
        return intent.getIntExtra("appWidgetId", -1);
    }

    private void showLoading(){
        publisher.update(forecastAppWidgetView.setLoading());
    }

    private void fetchForecast(int appWidgetId){
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "fetchWeatherData");
        }
        try {
            String locationName = appShared.get(String.valueOf(appWidgetId));
            cloudForecastCityDataStore.fetch();
            //TODO: tirar del datastore de forecast...o mejor meter un caso de uso, pa esconder el observable
            //TODO: recordar parar el servicio cuando responda....o al menos intentarlo
            //CloudForecastCityDataStore
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
        //TODO: proveer el datastore de forecast
        ForecastAppWidgetComponent component = DaggerForecastAppWidgetComponent.builder().forecastAppWidgetModule(new ForecastAppWidgetModule(context)).build();
        component.inject(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}