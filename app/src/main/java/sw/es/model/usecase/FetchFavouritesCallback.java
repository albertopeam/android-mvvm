package sw.es.model.usecase;

import sw.es.model.local.FavouriteLocation;

/**
 * Created by albertopenasamor on 27/10/15.
 */
public interface FetchFavouritesCallback {
    void onFavourite(FavouriteLocation favouriteLocation);
}
