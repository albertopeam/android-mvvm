package sw.es.dagger2;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import sw.es.view.activity.weather.FavouriteWeathersActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FavouriteWeatherActivityTest {

    @Rule
    public ActivityTestRule<FavouriteWeathersActivity> mActivityRule = new ActivityTestRule(FavouriteWeathersActivity.class);

    //TODO: explicacion de como mockear con dagger...
    //https://speakerdeck.com/jakewharton/dependency-injection-with-dagger-2-devoxx-2014
    //https://engineering.circle.com/instrumentation-testing-with-dagger-mockito-and-espresso/

    @Test
    public void testEmpty() {
        //TODO: mockear un viewmodel de palo....peta en la visibilidad....pq va demasiado rápido.
        //TODO: mock un viewmodel, o inyectar un viewModel  con dagger que ya lleve las "respuestas" moqueadas para este caso...
        //TODO: como hacer para inyectar diferentes viewModels en funcion del test a ejecutar, pej: vacio, con alguno
        //TODO: desactivar animaciones
        onView(withId(R.id.emptytextview)).check(matches(isDisplayed()));
    }
}