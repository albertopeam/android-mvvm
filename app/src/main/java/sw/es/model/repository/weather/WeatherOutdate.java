package sw.es.model.repository.weather;

import android.content.Context;
import android.content.SharedPreferences;

import org.joda.time.DateTime;
import org.joda.time.Period;

import sw.es.model.repository.outdate.Outdate;


/**
 * Created by AlbertoGarcia on 2/6/15.
 */
public class WeatherOutdate implements Outdate<Long> {

    private static final String SHARED_PREFERENCES = "shared_preferences";
    private static final String KEY_WEATHER_ID = "key_weather_id:";
    todo, descoplar las shared.... en un objeto más bonico
    private int minutesBeforeExpire;
    private SharedPreferences context;

    public WeatherOutdate(Context context, int minutesBeforeExpire) {
        this.context = context;
        this.minutesBeforeExpire = minutesBeforeExpire;
    }

    private SharedPreferences.Editor getEditor() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.edit();
    }

    private SharedPreferences getShared() {
        return context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    private String getWeatherDate(Long id) {
        return getShared().getString(KEY_WEATHER_ID + id, "");
    }

    @Override
    public boolean isExpired(Long id) {

        String weatherDate = getWeatherDate(id);
        if (weatherDate.isEmpty()) {
            return false;
        }
        DateTime previousDateTime = new DateTime(weatherDate);
        DateTime currentDate = DateTime.now();

        Period diff = new Period(previousDateTime, currentDate);
        int diffMin = diff.getMinutes();

        if (diffMin > minutesBeforeExpire) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public void setLastUpdate(Long id) {
        getEditor().putString(KEY_WEATHER_ID + id, DateTime.now().toString()).commit();
    }
}
