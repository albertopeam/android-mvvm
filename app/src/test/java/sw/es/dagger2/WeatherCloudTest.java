package sw.es.dagger2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import sw.es.model.backend.WeatherCloud;
import sw.es.model.backend.aux.Clouds;
import sw.es.model.backend.aux.Coordinates;
import sw.es.model.backend.aux.Main;
import sw.es.model.backend.aux.Sys;
import sw.es.model.backend.aux.Weather;
import sw.es.model.backend.aux.Wind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by albertopenasamor on 5/11/15.
 */
public class WeatherCloudTest {

    private WeatherCloud emptyWeatherCloud;
    private WeatherCloud weatherCloud;

    /**
     * Sets up the test fixture.
     * (Called before every test case method.)
     */
    @Before
    public void setUp() {


        //cloud without data
        emptyWeatherCloud = new WeatherCloud();

        //cloud with data
        Clouds clouds = new Clouds();
        clouds.all = 92.0d;

        weatherCloud = new WeatherCloud();
        weatherCloud.name = "Perillo";
        weatherCloud.cod = 200;
        weatherCloud.clouds = clouds;
        weatherCloud.coord = new Coordinates();
        weatherCloud.dt = Calendar.getInstance().getTime().getTime();
        weatherCloud.sys = new Sys();
        weatherCloud.wind = new Wind();
        weatherCloud.main = new Main();
        Weather[] innerWeather = new Weather[1];
        innerWeather[0] = new Weather();
        innerWeather[0].icon = "10d";
        innerWeather[0].description = "rain";
        weatherCloud.weather = innerWeather;
    }


    /**
     * Tears down the test fixture.
     * (Called after every test case method.)
     */
    @After
    public void tearDown() {
        emptyWeatherCloud = null;
        weatherCloud = null;
    }


    @Test
    public void testWeatherCloud(){
        assertEquals("description not match", "rain", weatherCloud.getDescription());
        assertEquals("name not match", "Perillo", weatherCloud.getName());
        assertEquals("icon not match", "10d", weatherCloud.getIcon());
        assertEquals("cloudines not match", 92.0d, weatherCloud.getCloudiness(), 0d);
        assertNotEquals("datetime match", 0, weatherCloud.getDateTime());
    }


    @Test
    public void testEmptyWeatherCloud(){
        assertNotNull("description is NULL", emptyWeatherCloud.getDescription());
        assertNotNull("name is NULL", emptyWeatherCloud.getName());
        assertNotNull("icon is NULL", emptyWeatherCloud.getIcon());
        assertEquals("diferent icon_cloudiness", emptyWeatherCloud.getCloudiness(), 0d, 0d);
        assertEquals("diferent datetime", emptyWeatherCloud.getDateTime(), 0d, 0d);
    }


    @Test(expected=NullPointerException.class)
    public void testForException() {
        int size = emptyWeatherCloud.weather.length;
    }
}
