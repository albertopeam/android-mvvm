package sw.es.domain.units;

/**
 * Created by albertopenasamor on 17/11/15.
 */
public class DistanceMetrics {
    public static double kmPerHour(double metersPerSecond){
        return metersPerSecond * 3.6d;
    }
}
