package sw.es.network;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;
import sw.es.model.backend.ForecastCityCloud;
import sw.es.model.backend.WeatherCloud;


/**
 * Created by albertopenasamor on 20/10/15.
 */
public interface WeatherBackendAPI {

    String appId = "appid=9186b8e5715f961fed5d4482516bc296";

    @GET("weather?" + appId)
    Observable<WeatherCloud> fetchWeather(@Query("q") String name);

    @GET("forecast?" + appId)
    Observable<ForecastCityCloud> fetchForecast(@Query("q") String name);
}
