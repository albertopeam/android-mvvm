package sw.es.dagger2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sw.es.model.local.FavouriteLocation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;


/**
 * Created by albertopenasamor on 5/11/15.
 */
public class FavouriteLocationTest {


    FavouriteLocation favouriteLocation;
    FavouriteLocation sameFavouriteLocation;
    FavouriteLocation differentFavouriteLocation;


    /**
     * Sets up the test fixture.
     * (Called before every test case method.)
     */
    @Before
    public void setUp() {
        favouriteLocation = new FavouriteLocation("Perillo");
        sameFavouriteLocation = new FavouriteLocation("Perillo");
        differentFavouriteLocation = new FavouriteLocation("Oleiros");
    }


    /**
     * Tears down the test fixture.
     * (Called after every test case method.)
     */
    @After
    public void tearDown() {
        sameFavouriteLocation = null;
        favouriteLocation = null;
        differentFavouriteLocation = null;
    }


    @Test
    public void testFavoriteLocation(){
        assertThat(favouriteLocation.getName(), equalTo("Perillo"));
        assertThat(favouriteLocation, equalTo(sameFavouriteLocation));
        assertThat(favouriteLocation, not(equalTo(differentFavouriteLocation)));
    }


}
