package sw.es.model.backend;

import sw.es.model.backend.aux.Clouds;
import sw.es.model.backend.aux.Main;
import sw.es.model.backend.aux.Sys;
import sw.es.model.backend.aux.Weather;
import sw.es.model.backend.aux.Wind;

/**
 * Created by alberto on 9/11/15.
 */
public class ForecastCloud {
    int dt;
    String dt_txt;
    public Weather[] weather;
    public Main main;
    public Wind wind;
    public Clouds clouds;
    public Sys sys;
}
