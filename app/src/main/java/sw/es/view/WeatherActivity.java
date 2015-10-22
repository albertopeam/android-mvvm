package sw.es.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import sw.es.dagger2.R;
import sw.es.model.local.Weather;
import sw.es.viewmodel.weather.WeatherListener;
import sw.es.viewmodel.weather.WeatherViewModel;

//TODO: falta el databinding
public class WeatherActivity extends AppCompatActivity implements WeatherListener {


    @Inject WeatherViewModel viewModel;
    @Bind(R.id.toolbar) Toolbar toolbar;


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
    protected void onDestroy() {
        super.onDestroy();
        viewModel.destroy();
    }


    @Override
    public void onWeather(Weather weather) {
        //TODO: binding... comunication
    }


    private void inject() {
        //TODO: hacer las inyecciones con dagger
    }


    private void initViewModel() {
        viewModel.setup(this);
        viewModel.load();
    }
}
