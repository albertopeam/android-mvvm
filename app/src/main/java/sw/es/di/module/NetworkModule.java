package sw.es.di.module;

import dagger.Module;
import dagger.Provides;
import sw.es.network.WeatherApiFactory;
import sw.es.network.WeatherBackendAPI;

@Module
public class NetworkModule {

    public NetworkModule() {}

    @Provides
    WeatherBackendAPI provideWeatherBackendAPI() {
        return WeatherApiFactory.createRX();
    }

}
