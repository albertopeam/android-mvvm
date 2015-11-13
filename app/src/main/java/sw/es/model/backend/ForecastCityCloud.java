package sw.es.model.backend;

import java.util.ArrayList;
import java.util.List;

import sw.es.model.backend.aux.City;

/**
 * Created by alberto on 9/11/15.
 */
public class ForecastCityCloud extends AbstractCloud{

    public City city;
    public List<ForecastCloud> list;

    public int getCod() {
        return cod;
    }

    public int getCityId() {
        return city!=null?city.id:0;
    }

    public String getCityName(){
        return city!=null?city.name:"";
    }

    public List<ForecastCloud> getList() {
        if (list == null){
            return new ArrayList<>();
        }
        return list;
    }
}
