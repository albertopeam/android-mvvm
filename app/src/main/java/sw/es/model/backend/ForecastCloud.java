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
    String dt_txt; //is the same that dt, but it comes formatted, pej: 2015-11-16 15:00:00
    public Weather[] weather;
    public Main main;
    public Wind wind;
    public Clouds clouds;
    public Sys sys;

    public double getHumidity(){
        return main != null?main.humidity:0d;
    }

    public double getPressure(){
        return main != null?main.pressure:0d;
    }

    public double getTemp(){
        return main != null?main.temp:0d;
    }

    public double getWindSpeed(){
        return wind != null?wind.speed:0d;
    }

    public double getWindDegree(){
        return wind != null?wind.deg:0d;
    }

    public long getDateTime() {
        return dt;
    }

    public double getCloudiness(){
        return clouds != null?clouds.all:0.0;
    }

    public String getDescription(){
        return (weather != null && weather.length > 0)?weather[0].description:"";
    }

    public int getIconId(){
        return (weather != null && weather.length > 0)?weather[0].id:0;
    }

    public String getIcon(){
        return (weather != null && weather.length > 0)?weather[0].icon:"";
    }

    public double getTempMax(){
        return main != null?main.temp_max:0d;
    }

    public double getTempMin(){
        return main != null?main.temp_min:0d;
    }
}
