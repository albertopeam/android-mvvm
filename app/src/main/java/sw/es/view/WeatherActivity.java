package sw.es.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import sw.es.dagger2.R;
import sw.es.model.local.Weather;
import sw.es.viewmodel.WeatherViewModel;

public class WeatherActivity extends AppCompatActivity implements WeatherViewModel.WeatherListener {

    //private W
    private WeatherViewModel viewModel;
    @Bind(R.id.toolbar) Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO:
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        viewModel = new WeatherViewModel(this);
        setSupportActionBar(toolbar);
    }


    @Override
    public void onWeatherChanged(List<Weather> weatherList) {

    }
}
