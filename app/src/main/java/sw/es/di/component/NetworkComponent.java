package sw.es.di.component;

import javax.inject.Singleton;

import dagger.Component;
import sw.es.di.module.NetworkModule;
import sw.es.network.WeatherBackendAPI;

/**
 * Created by albertopenasamor on 22/10/15.
 */
@Singleton
@Component(
        modules = {
                NetworkModule.class
        }
)
public interface NetworkComponent {
    WeatherBackendAPI provideWeatherBackendAPI();
}
