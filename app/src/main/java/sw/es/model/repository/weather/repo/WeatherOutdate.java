package sw.es.model.repository.weather.repo;

import org.joda.time.DateTime;
import org.joda.time.Period;

import javax.inject.Inject;

import sw.es.model.local.Weather;
import sw.es.model.repository.outdate.Outdate;
import sw.es.model.sharedprefs.AppShared;


/**
 * Created by Alberto Penas on 22/10/15.
 */
public class WeatherOutdate implements Outdate<String, Weather> {


    private static final String KEY_WEATHER_ID = "key_weather_last_update";
    private int minutsBetweenUpdates;
    private AppShared appShared;


    @Inject
    public WeatherOutdate(AppShared appShared, int minutes) {
        this.appShared = appShared;
        this.minutsBetweenUpdates = minutes;
    }


    @Override
    public boolean isExpired(String name) {
        String weatherDate = appShared.get(key(name));
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
    public void setLastUpdate(Weather weather) {
        appShared.put(key(weather.getName()), DateTime.now().toString());
    }


    private String key(String name){
        return String.format("%s:%s",KEY_WEATHER_ID, name);
    }
}
