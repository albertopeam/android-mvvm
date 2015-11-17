package sw.es.appwidget;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import javax.inject.Inject;

import sw.es.appwidget.view.ForecastAppWidgetView;
import sw.es.di.component.DaggerForecastAppWidgetComponent;
import sw.es.di.component.ForecastAppWidgetComponent;
import sw.es.di.module.ForecastAppWidgetModule;
import sw.es.domain.repository.forecast.usecase.ForecastFetchUseCase;
import sw.es.domain.repository.forecast.usecase.UseCaseCallback;
import sw.es.domain.sharedprefs.AppShared;
import sw.es.model.local.Forecast;

import static android.util.Log.e;
import static sw.es.dagger2.BuildConfig.DEBUG;

//TODO: repo: crear, probar e inyectar
//TODO: migrar de los putos services infernales....¿pueden correr varios a la vez?
public class ForecastAppWidgetService extends Service implements UseCaseCallback<String, Forecast> {


    private static final String TAG = ForecastAppWidgetService.class.getSimpleName();
    private int startId;
    private int appWidgetId;
    @Inject ForecastAppWidgetView forecastAppWidgetView;
    @Inject AppWidgetPublisher<ForecastAppWidget>publisher;
    @Inject ForecastFetchUseCase forecastFetchUseCase;
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
        if (DEBUG){
            Log.d(TAG, "onStartCommand");
        }

        showLoading();
        fetchForecast(getAppWidgetId(intent));

        return Service.START_STICKY;
    }


    private void setStartId(int startId){
        if (DEBUG) {
            e(TAG, "startId: " + startId);
        }
        this.startId = startId;
    }


    private int getAppWidgetId(Intent intent){
        if (DEBUG) {
            e(TAG, "getAppWidgetId");
        }

        appWidgetId = intent.getIntExtra("appWidgetId", -1);
        if (DEBUG) {
            e(TAG, "appWidgetId: " + appWidgetId);
        }
        return appWidgetId;
    }


    private String getLocationName(int appWidgetId){
        if (DEBUG) {
            e(TAG, "getLocationName");
        }
        String locationName = appShared.get(String.valueOf(appWidgetId));
        if (DEBUG) {
            e(TAG, "locationName: " + locationName);
        }
        return locationName;
    }


    private void showLoading(){
        if (DEBUG) {
            e(TAG, "showLoading");
        }
        publisher.update(forecastAppWidgetView.setLoading());
    }


    private void fetchForecast(int appWidgetId){
        if (DEBUG) {
            Log.d(TAG, "fetchWeatherData");
        }

        String locationName = getLocationName(appWidgetId);
        forecastFetchUseCase.run(locationName, this);
    }


    private void initializeInjections(Context context){
        ForecastAppWidgetComponent component = DaggerForecastAppWidgetComponent.builder().forecastAppWidgetModule(new ForecastAppWidgetModule(context)).build();
        component.inject(this);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onResult(String params, Forecast forecast) {
        if (DEBUG) {
            e(TAG, "onResult");
        }
        publisher.update(appWidgetId, forecastAppWidgetView.setForecast(forecast));
        stop();
    }


    @Override
    public void onResultError(String params, Throwable throwable) {
        if (DEBUG) {
            e(TAG, "onResultError");
            if (throwable != null){
                throwable.printStackTrace();
            }
        }
        publisher.update(appWidgetId, forecastAppWidgetView.setError(throwable));
        stop();
    }

    private void stop(){
        if (DEBUG) {
            e(TAG, "stop");
        }
        this.stopSelf(startId);
    }
}