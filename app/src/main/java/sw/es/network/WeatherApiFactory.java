package sw.es.network;

/**
 * Created by albertopenasamor on 20/10/15.
 */
public class WeatherApiFactory extends ServiceFactory{

    private static String BACKEND_URL = "http://api.openweathermap.org/data/2.5/";

    public static WeatherBackendAPI createRX() {
        return createRetrofitRxService(WeatherBackendAPI.class, BACKEND_URL);
    }
}
