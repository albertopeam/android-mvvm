package sw.es.model.local;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import sw.es.domain.units.CompassRoseMetrics;
import sw.es.domain.units.DistanceMetrics;
import sw.es.domain.units.TemperatureMetrics;

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

    public String getTempInCelsiusC() {
        return String.format("%.0fºC", TemperatureMetrics.tempInCelsius(temp));
    }

    public double getTempMin() {
        return tempMin;
    }

    public String getTempMinInCelsius() {
        return String.format("%.0fºC", TemperatureMetrics.tempInCelsius(tempMin));
    }

    public double getTempMax() {
        return tempMax;
    }

    public String getTempmaxInCelsius() {
        return String.format("%.0fºC", TemperatureMetrics.tempInCelsius(tempMax));
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public String getWindSpeedInKM() {
        return String.format("%.0fKm/h", DistanceMetrics.kmPerHour(windSpeed));
    }

    public double getWindDegree() {
        return windDegree;
    }

    public String getWindDegrees() {
        return String.format("%s", CompassRoseMetrics.direction(windDegree));
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public void setCloudiness(double cloudiness) {
        this.cloudiness = cloudiness;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDegree(double windDegree) {
        this.windDegree = windDegree;
    }


    public String getDatetimeFormatted() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm, EEE");
        String outDate = formatter.print(datetime*1000L);
        return outDate;
    }
}
