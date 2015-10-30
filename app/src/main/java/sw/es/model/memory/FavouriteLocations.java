package sw.es.model.memory;

import java.util.ArrayList;
import java.util.List;

import sw.es.model.local.FavouriteLocation;

/**
 * Created by albertopenasamor on 30/10/15.
 */
public class FavouriteLocations {


    private List<FavouriteLocation> favouriteLocationList;


    public FavouriteLocations() {
        this.favouriteLocationList = new ArrayList<>();
    }


    public void add(String name){
        FavouriteLocation favouriteLocation = new FavouriteLocation(name);
        favouriteLocationList.add(favouriteLocation);
    }


    public void add(FavouriteLocation favouriteLocation){
        favouriteLocationList.add(favouriteLocation);
    }


    public void remove(String name){
        FavouriteLocation favouriteLocation = find(name);
        favouriteLocationList.remove(favouriteLocation);
    }


    public boolean has(String name){
        return find(name) != null;
    }


    public boolean hasNot(String name){
        return find(name) == null;
    }


    public FavouriteLocation find(String name){
        for (FavouriteLocation favouriteLocation:favouriteLocationList){
            if (favouriteLocation.getName().equals(name)){
                return favouriteLocation;
            }
        }
        return null;
    }

}
