package sw.es.network;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;
import sw.es.model.backend.WeatherCloud;


/**
 * Created by albertopenasamor on 20/10/15.
 */
public interface WeatherBackendAPI {

    @GET("q={name}&appid=9186b8e5715f961fed5d4482516bc296")
    Observable<WeatherCloud> fetchWeather(@Path("name") String name);
}
