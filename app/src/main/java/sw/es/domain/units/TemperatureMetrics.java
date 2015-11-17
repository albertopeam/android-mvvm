package sw.es.domain.units;

/**
 * Created by albertopenasamor on 17/11/15.
 */
public class TemperatureMetrics {
    public static double tempInCelsius(double tempInKelvin){
        return tempInKelvin - 273.15d;
    }
}
