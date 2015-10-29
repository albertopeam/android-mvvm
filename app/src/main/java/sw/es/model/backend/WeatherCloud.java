package sw.es.model.backend;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public class WeatherCloud {


    public int id;
    public String name;
    public String message;
    public int cod;
    public long dt;
    public Coordinates coord;
    public Weather[] weather;
    public Main main;
    public Wind wind;
    public Clouds clouds;
    public Sys sys;


    public WeatherCloud() {}


    public boolean isSuccess(){
        return cod == 200;
    }

    public String getErrorMessage(){
        return message != null?message:"";
    }


    public static class Coordinates{
        public double lon;
        public double lat;
    }


    public static class Weather{
        public int id;
        public String main;
        public String description;
        public String icon;
    }


    public static class Main{
        public double temp;
        public double pressure;
        public double humidity;
        public double temp_min;
        public double temp_max;
        public double sea_level;
        public double grnd_level;
    }


    public static class Wind{
        public double speed;
        public double deg;
    }


    public static class Clouds{
        public double all;
    }


    public static class Sys{
        public double message;
        public String country;
        public long sunrise;
        public long sunset;
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
