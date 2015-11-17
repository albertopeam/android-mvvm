package sw.es.domain.sharedprefs.usecase;

import java.util.List;

import sw.es.model.local.FavouriteLocation;

/**
 * Created by albertopenasamor on 27/10/15.
 */
public interface FetchFavCallback {
    void onFavourites(List<FavouriteLocation> favouriteLocation);
    void onEmptyFavourites();
}
