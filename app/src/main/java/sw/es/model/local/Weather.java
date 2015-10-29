package sw.es.model.local;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by albertopenasamor on 27/5/15.
 */
public class Weather {

    private long id;
    private String name;
    private String description;
    private String icon;
    private double cloudiness;
    private long datetime;
    private double latitude;
    private double longitude;
    private double temp;
    private double pressure;
    private double humidity;
    private double windSpeed;

    public Weather() {}

    public long getRemoteId() {
        return id;
    }

    public String getName() {
        return name!=null?name:"";
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getTemp() {
        return temp;
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

    public long getDatetime() {
        return datetime;
    }

    public String getDescription() {
        return description;
    }

    public double getCloudiness() {
        return cloudiness;
    }

    public String getTempFormatted() {
        return String.format("%.0f", temp - 273.15d);
    }

    public String getPressureFormatted() {
        return String.valueOf(pressure);
    }

    public String getHumidityFormatted() {
        return String.valueOf(humidity);
    }

    public String getWindSpeedFormatted() {
        //m/s
        return String.valueOf(windSpeed);
    }

    public String getDatetimeFormatted() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm, EEEE, dd MMMM yyyy");
        String outDate = formatter.print(datetime*1000L);
        return outDate;
    }

    public String getDescriptionFormatted() {
        return description;
    }

    public String getCloudinessFormatted() {
        return String.valueOf(cloudiness);
    }

    public String getIcon() {
        return icon;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setTemp(double temp) {
        this.temp = temp;
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

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public void setCloudiness(double cloudiness) {
        this.cloudiness = cloudiness;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
