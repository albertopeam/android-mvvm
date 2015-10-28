package sw.es.model.local;

/**
 * Created by albertopenasamor on 27/10/15.
 */
public class FavouriteLocation {

    private String name;

    public FavouriteLocation(String name) {
        this.name = name;
    }

    public String getName() {
        return name!=null?name:"";
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof FavouriteLocation){
            FavouriteLocation favouriteLocation = (FavouriteLocation) o;
            return this.getName().equalsIgnoreCase(favouriteLocation.getName());
        }else{
            return false;
        }
    }
}
