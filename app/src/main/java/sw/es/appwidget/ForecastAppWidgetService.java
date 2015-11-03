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

//TODO: repo
public class ForecastAppWidgetService extends Service {

    private static final String TAG = ForecastAppWidgetService.class.getSimpleName();
    private CompositeSubscription mCompositeSubscription = null;

    @Inject
    ForecastAppWidgetView forecastAppWidgetView;


    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, ForecastAppWidgetService.class);
        return intent;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        /*
        WeatherAppWidgetComponent component = DaggerWeatherAppWidgetComponent.builder()
                .weatherAppWidgetModule(new WeatherAppWidgetModule(this))
                .build();
        component.inject(this);
        */
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (BuildConfig.DEBUG){
            Log.d(TAG, "onStartCommand");
        }
        if (!isRunning()){
            fetchWeatherData();
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
        if (!mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
        mCompositeSubscription = null;
    }


    private void fetchWeatherData(){
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "fetchWeatherData");
        }
        //TODO:
        /*
        mCompositeSubscription = new CompositeSubscription();

        LocationService locationService = new LocationService(this);
        final Observable locationObservable = locationService.getLocation()
                .timeout(LOCATION_UPDATE_TIMEOUT, TimeUnit.SECONDS)
                .flatMap(new Func1<Location, Observable<CurrentWeather>>() {
                    @Override
                    public Observable<CurrentWeather> call(Location location) {
                        if (BuildConfig.DEBUG) {
                            Log.d(TAG, "locationService call");
                        }
                        return new WeatherService().fetchCurrentWeather(location.getLongitude(), location.getLatitude())
                                .map(new Func1<CurrentWeather, CurrentWeather>() {
                                    @Override
                                    public CurrentWeather call(CurrentWeather currentWeather) {
                                        return currentWeather;
                                    }
                                });
                    }
                });

        mCompositeSubscription.add(locationObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CurrentWeather>() {
                    @Override
                    public void onCompleted() {
                        if (BuildConfig.DEBUG) {
                            Log.d(TAG, "onCompleted");
                        }
                        stopSelf();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (BuildConfig.DEBUG) {
                            Log.d(TAG, "onErrors");
                        }
                        updateAppWidgetsWithError(e);
                        stopSelf();
                    }

                    @Override
                    public void onNext(CurrentWeather currentWeather) {
                        if (BuildConfig.DEBUG) {
                            Log.d(TAG, "onNext");
                        }
                        updateAppWidgets(currentWeather);
                    }
                }));
                */

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

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}