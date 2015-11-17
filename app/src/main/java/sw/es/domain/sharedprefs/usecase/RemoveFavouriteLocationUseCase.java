package sw.es.domain.sharedprefs.usecase;

import javax.inject.Inject;

import sw.es.domain.sharedprefs.AppShared;

/**
 * Created by albertopenasamor on 27/10/15.
 */
public class RemoveFavouriteLocationUseCase implements UseCase<String>{


    private AppShared appShared;


    @Inject
    public RemoveFavouriteLocationUseCase(AppShared appShared){
        this.appShared = appShared;
    }

    @Override
    public void run(String s) {
        appShared.removeStringFromSet(FavoriteLocationKeys.KEY_FAVOURITES, s);
    }
}
