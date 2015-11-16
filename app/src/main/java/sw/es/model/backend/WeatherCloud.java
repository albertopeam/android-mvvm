package sw.es.model.backend;

import sw.es.model.backend.aux.Clouds;
import sw.es.model.backend.aux.Coordinates;
import sw.es.model.backend.aux.Main;
import sw.es.model.backend.aux.Sys;
import sw.es.model.backend.aux.Weather;
import sw.es.model.backend.aux.Wind;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public class WeatherCloud extends AbstractCloud {

    public int id;
    public String name;
    public String message;
    public long dt;
    public Coordinates coord;
    public Weather[] weather;
    public Main main;
    public Wind wind;
    public Clouds clouds;
    public Sys sys;

    public WeatherCloud() {}

    public String getErrorMessage(){
        return message != null?message:"";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name!= null?name:"";
    }

    public double getLatitude(){
        return coord != null?coord.lat:0d;
    }

    public double getLongitude(){
        return coord != null?coord.lon:0d;
    }



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

    public long getDateTime() {
        return dt;
    }

    public double getCloudiness(){
        return clouds != null?clouds.all:0.0;
    }

    public String getDescription(){
        return (weather != null && weather.length > 0)?weather[0].description:"";
    }

    public String getIcon(){
        return (weather != null && weather.length > 0)?weather[0].icon:"";
    }
}
