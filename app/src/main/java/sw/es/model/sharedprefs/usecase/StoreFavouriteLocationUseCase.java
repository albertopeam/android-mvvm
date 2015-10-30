package sw.es.model.sharedprefs.usecase;

import javax.inject.Inject;

import sw.es.model.sharedprefs.AppShared;

/**
 * Created by albertopenasamor on 27/10/15.
 */
public class StoreFavouriteLocationUseCase implements UseCase<String>{


    private AppShared appShared;


    @Inject
    public StoreFavouriteLocationUseCase(AppShared appShared){
        this.appShared = appShared;
    }

    @Override
    public void run(String s) {
        appShared.addStringToSet(FavoriteLocationKeys.KEY_FAVOURITES, s);
    }
}
