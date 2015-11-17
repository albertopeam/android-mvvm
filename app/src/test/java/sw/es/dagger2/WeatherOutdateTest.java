package sw.es.dagger2;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sw.es.domain.repository.weather.repo.WeatherOutdate;
import sw.es.domain.sharedprefs.AppShared;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by alberto on 8/11/15.
 */
public class WeatherOutdateTest {

    String name = "Perillo";
    AppShared appShared;
    int minutesToExpire = 1;
    int minusMinutes = 5;
    WeatherOutdate weatherOutdate;


    @Before
    public void setUp() {
        appShared = mock(AppShared.class);
        weatherOutdate = new WeatherOutdate(appShared, minutesToExpire);
    }

    @Test
    public void testNotExpired(){
        when(appShared.get(WeatherOutdate.KEY_WEATHER_ID + WeatherOutdate.SEPARATOR + name)).thenReturn(DateTime.now().toString());
        assertEquals("Not Expired failed", weatherOutdate.isExpired(name), false);
    }

    @Test
    public void testExpired(){
        when(appShared.get(WeatherOutdate.KEY_WEATHER_ID + WeatherOutdate.SEPARATOR + name)).thenReturn(DateTime.now().minusMinutes(minusMinutes).toString());
        assertEquals("Expirated failed", weatherOutdate.isExpired(name), true);
    }

    @After
    public void tearDown() {
        name = null;
        appShared = null;
        weatherOutdate = null;
    }
}
