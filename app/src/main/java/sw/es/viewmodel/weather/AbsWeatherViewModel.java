package sw.es.viewmodel.weather;

import sw.es.viewmodel.abs.ViewModel;

/**
 * Created by albertopenasamor on 22/10/15.
 */
public interface AbsWeatherViewModel extends ViewModel{
    void setup(WeatherListener weatherListener);
    void load();
    void fetch(String localityName);
}
