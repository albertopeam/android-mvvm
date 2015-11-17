package sw.es.domain.units;

/**
 * Created by albertopenasamor on 17/11/15.
 */
public class CompassRoseMetrics {
    private static String[] compassRose = {"E", "ENE", "NE", "NNE", "N", "NNW", "NW", "WNW", "W", "WSW", "SW", "SSW", "S", "SSE", "SE", "ESE"};
    private static double roseCompassDivision = 22.5d;

    public static String direction(double degrees){
        int position = (int) (degrees/roseCompassDivision);
        return compassRose[position];
    }
}
