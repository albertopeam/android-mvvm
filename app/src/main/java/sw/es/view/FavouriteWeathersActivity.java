package sw.es.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import sw.es.dagger2.R;
import sw.es.di.component.DaggerFavouriteWeathersViewModelComponent;
import sw.es.di.component.FavouriteWeathersViewModelComponent;
import sw.es.di.module.FavouriteWeathersViewModelModule;
import sw.es.model.local.FavouriteLocation;
import sw.es.model.local.Weather;
import sw.es.viewmodel.weather.FavouriteWeathersListener;
import sw.es.viewmodel.weather.FavouriteWeathersViewModel;

import static android.util.Log.e;
import static sw.es.dagger2.BuildConfig.DEBUG;

//TODO: falta el databinding
public class FavouriteWeathersActivity extends BaseActivity implements FavouriteWeathersListener, SearchView.OnQueryTextListener {


    private static final String TAG = FavouriteWeathersActivity.class.getSimpleName();
    @Inject FavouriteWeathersViewModel viewModel;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.recycler) RecyclerView recyclerView;
    @Bind(R.id.progressbar) ProgressBar progressBar;
    @Bind(R.id.view) View view;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: binding.... revisar como va
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        initViewModel();
    }

    @Override
    protected void initializeInjector() {
        FavouriteWeathersViewModelComponent component = DaggerFavouriteWeathersViewModelComponent.builder()
                .applicationComponent(getApplicationComponent())
                .weatherViewModelModule(new FavouriteWeathersViewModelModule())
                .build();
        component.inject(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.destroy();
    }


    @Override
    public void onWeather(Weather weather) {
        //TODO: binding... comunication
        if (DEBUG) {
            e(TAG, "onWeather");
        }
    }


    @Override
    public void onWeatherError(Throwable throwable) {
        //TODO: error, si no hay nada...o snack...no se
        if (DEBUG) {
            e(TAG, "onWeatherError: ");
            throwable.printStackTrace();
        }
    }

    @Override
    public void alreadyHasFavouriteLocation(FavouriteLocation favouriteLocation) {
        Snackbar.make(view, getResources().getString(R.string.text_already_added_location), Snackbar.LENGTH_LONG).show();
    }


    private void initViewModel() {
        viewModel.setup(this);
        viewModel.load();
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        e("onQueryTextChange", query);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null){
            imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
        }
        viewModel.pull(query);
        return true;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
