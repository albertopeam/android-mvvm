package sw.es.model.repository.weather.repo;

import org.joda.time.DateTime;
import org.joda.time.Period;

import javax.inject.Inject;
import javax.inject.Singleton;

import sw.es.model.repository.outdate.Outdate;
import sw.es.model.sharedprefs.AppShared;


/**
 * Created by Alberto Penas on 22/10/15.
 */
//TODO: así no actualiza viejas entradas, aunque se cumpla la condición,
/**
 * pej: con un outdate de n = 1h
 *
 * hora: 0
 * pides perillo => weather
 * outdate = ahora
 *
 * hora: 30m
 * pides Montrove => weather
 * outdate = 30
 *
 * hora: 65m
 * pides perillo => weather de base de datos, pq el último outdate solo tiene 35m!!!!!
 * outdate = 65
 *
 **/

@Singleton
public class WeatherOutdate implements Outdate<Long> {


    private static final String KEY_WEATHER_ID = "key_weather_last_update";
    private int minutsBetweenUpdates;
    private AppShared appShared;


    @Inject
    public WeatherOutdate(AppShared appShared, int minutesBetweenUpdates) {
        this.appShared = appShared;
        this.minutsBetweenUpdates = minutesBetweenUpdates;
    }


    @Override
    public boolean isExpired() {
        String weatherDate = appShared.get(KEY_WEATHER_ID);
        if (weatherDate.isEmpty()) {
            return true;
        }
        DateTime previousDateTime = new DateTime(weatherDate);
        DateTime currentDate = DateTime.now();

        Period diff = new Period(previousDateTime, currentDate);
        int diffMinutes = diff.getMinutes();

        if (diffMinutes > minutsBetweenUpdates) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void setLastUpdate(Long id) {
        appShared.put(KEY_WEATHER_ID, DateTime.now().toString());
    }
}
