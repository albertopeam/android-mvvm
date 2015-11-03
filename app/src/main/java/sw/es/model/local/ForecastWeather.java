package sw.es.model.local;

/**
 * Created by albertopenasamor on 3/11/15.
 */
public class ForecastWeather {

    private String description;
    private String icon;
    private int iconId;
    private double cloudiness;
    private long datetime;
    private double temp;
    private double tempMin;
    private double tempMax;
    private double pressure;
    private double humidity;
    private double windSpeed;
    private double windDegree;


    public String getDescription() {
        return description!=null?description:"";
    }

    public String getIcon() {
        return icon!=null?icon:"";
    }

    public int getIconId() {
        return iconId;
    }

    public double getCloudiness() {
        return cloudiness;
    }

    public long getDatetime() {
        return datetime;
    }

    public double getTemp() {
        return temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindDegree() {
        return windDegree;
    }
}
