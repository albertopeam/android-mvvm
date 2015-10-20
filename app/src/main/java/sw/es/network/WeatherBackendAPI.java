package sw.es.network;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;
import sw.es.model.backend.WeatherCloud;


/**
 * Created by albertopenasamor on 20/10/15.
 */
public interface WeatherBackendAPI {

    //TODO: registrarse, necesario api key: esta es de prueba....
    @GET("lat={latitude}&lon={longitude}&appid=bd82977b86bf27fb59a04b61b657fb6f")
    Observable<WeatherCloud> fetchWeather(@Path("latitude") double latitude, @Path("longitude") double longitude);
}
