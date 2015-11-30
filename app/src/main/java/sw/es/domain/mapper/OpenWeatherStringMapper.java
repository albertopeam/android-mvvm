package sw.es.domain.mapper;

import android.content.res.Resources;

import sw.es.dagger2.R;

/**
 * Created by alberto on 22/11/15.
 */
public class OpenWeatherStringMapper extends StringMapper{


    public OpenWeatherStringMapper(Resources resources) {
        super(resources);
    }

    @Override
    protected String resFor(Resources resources, String name) {
        if (name.equals("light rain")){
            return resources.getString(R.string.light_rain);
        }else if (name.equals("moderate rain")){
            return resources.getString(R.string.moderate_rain);
        }else if (name.equals("heavy intensity rain")){
            return resources.getString(R.string.heavy_intensity_rain);
        }else if (name.equals("clear sky") || name.equals("sky is clear")){
            return resources.getString(R.string.clear_sky);
        }else if (name.equals("few clouds")){
            return resources.getString(R.string.few_clouds);
        }else if (name.equals("scattered clouds")){
            return resources.getString(R.string.scattered_clouds);
        }else if (name.equals("broken clouds")){
            return resources.getString(R.string.broken_clouds);
        }else if (name.equals("overcast clouds")){
            return resources.getString(R.string.overcast_clouds);
        }else if (name.equals("shower rain")){
            return resources.getString(R.string.shower_rain);
        }else if (name.equals("thunderstorm")){
            return resources.getString(R.string.thunderstorm);
        }else if (name.equals("rain")){
            return resources.getString(R.string.rain);
        }else if (name.equals("snow")){
            return resources.getString(R.string.snow);
        }else if (name.equals("mist")){
            return resources.getString(R.string.mist);
        }
        return resForDefault(resources);
    }

    @Override
    protected String resForDefault(Resources resources) {
        return "not translated";
    }
}
