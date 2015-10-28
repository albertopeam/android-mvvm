package sw.es.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sw.es.dagger2.R;
import sw.es.dagger2.databinding.RowWeatherBinding;
import sw.es.model.local.Weather;

/**
 * Created by albertopenasamor on 15/10/15.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {


    private List<Weather> weatherList;


    public WeatherAdapter() {
        this.weatherList = new ArrayList<>();
    }


    public void addWeather(Weather weather) {
        weatherList.add(weather);
        notifyDataSetChanged();
    }


    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_weather, parent, false);
        WeatherViewHolder weatherViewHolder = new WeatherViewHolder(view);
        return weatherViewHolder;
    }


    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        final Weather weather = weatherList.get(position);
        holder.getBinding().setWeather(weather);
        holder.getBinding().executePendingBindings();
    }


    @Override
    public int getItemCount() {
        return weatherList.size();
    }


    public static class WeatherViewHolder extends RecyclerView.ViewHolder {

        final private RowWeatherBinding binding;

        public WeatherViewHolder(View view) {
            super(view);
            this.binding = DataBindingUtil.bind(view);
        }

        public RowWeatherBinding getBinding() {
            return binding;
        }
    }
}
