package sw.es.domain.mapper;

import android.content.res.Resources;

import sw.es.dagger2.R;

/**
 * Created by albertopenasamor on 19/11/15.
 */
public class MaterialDesignIconMapper extends IconMapper{


    public MaterialDesignIconMapper(Resources resources) {
        super(resources);
    }

    @Override
    protected int resFor(Resources resources, String iconCode) {
        if (iconCode.equalsIgnoreCase("01d")){
            return R.drawable.icon_weather_sunny;
        }else if (iconCode.equalsIgnoreCase("02d")){
            return R.drawable.icon_weather_partlycloudy;
        }else if (iconCode.equalsIgnoreCase("03d")){
            return R.drawable.icon_weather_cloudy;
        }else if (iconCode.equalsIgnoreCase("04d")){
            return R.drawable.icon_weather_cloudy;
        }else if (iconCode.equalsIgnoreCase("09d")){
            return R.drawable.icon_weather_pouring;
        }else if (iconCode.equalsIgnoreCase("10d")){
            return R.drawable.icon_weather_rainy;
        }else if (iconCode.equalsIgnoreCase("11d")){
            return R.drawable.icon_weather_lightning;
        }else if (iconCode.equalsIgnoreCase("13d")){
            return R.drawable.icon_weather_snowy;
        }else if (iconCode.equalsIgnoreCase("50d")){
            return R.drawable.icon_weather_fog;
        }else if (iconCode.equalsIgnoreCase("01n")){
            return R.drawable.icon_weather_night;
        }else if (iconCode.equalsIgnoreCase("02n")){
            return R.drawable.icon_weather_partlycloudy;
        }else if (iconCode.equalsIgnoreCase("03n")){
            return R.drawable.icon_weather_cloudy;
        }else if (iconCode.equalsIgnoreCase("04n")){
            return R.drawable.icon_weather_cloudy;
        }else if (iconCode.equalsIgnoreCase("09n")){
            return R.drawable.icon_weather_pouring;
        }else if (iconCode.equalsIgnoreCase("10n")){
            return R.drawable.icon_weather_rainy;
        }else if (iconCode.equalsIgnoreCase("11n")){
            return R.drawable.icon_weather_lightning;
        }else if (iconCode.equalsIgnoreCase("13n")){
            return R.drawable.icon_weather_snowy;
        }else if (iconCode.equalsIgnoreCase("50n")){
            return R.drawable.icon_weather_fog;
        }


        return resForDefault(resources);
    }

    @Override
    protected int resForDefault(Resources resources) {
        return R.drawable.icon_unknow;
    }


}
