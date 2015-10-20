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
    public Weather weather;
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
        public int all;
    }


    public static class Sys{
        public double message;
        public String country;
        public long sunrise;
        public long sunset;
    }
}
