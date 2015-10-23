package sw.es.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import sw.es.dagger2.R;
import sw.es.model.local.Weather;
import sw.es.viewmodel.weather.WeatherListener;
import sw.es.viewmodel.weather.WeatherViewModel;

//TODO: falta el databinding
public class WeatherActivity extends AppCompatActivity implements WeatherListener, SearchView.OnQueryTextListener {


    @Inject WeatherViewModel viewModel;
    @Bind(R.id.toolbar) Toolbar toolbar;
    //TODO: el search view quizas se puede redirigir al viewmodel, aunque está en el toolbar....en los menús....igual tiene que quedar aquí
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: binding.... revisar como va
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        inject();
        initViewModel();
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
    }


    @Override
    public void onWeatherError(Throwable throwable) {
        //TODO: error, si no hay nada...o snack...no se
    }


    private void inject() {
        //TODO: hacer las inyecciones con dagger
    }


    private void initViewModel() {
        viewModel.setup(this);
        viewModel.load();
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("onQueryTextChange", query);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null){
            imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
        }
        viewModel.fetch(query);
        return true;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
