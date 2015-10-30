package sw.es.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sw.es.dagger2.R;
import sw.es.dagger2.databinding.RowWeatherBinding;
import sw.es.model.local.Weather;
import sw.es.view.adapter.dragandswipe.ItemTouchHelperAdapter;

/**
 * Created by albertopenasamor on 15/10/15.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> implements ItemTouchHelperAdapter {


    private List<Weather> weatherList;
    private AdapterEvent<String> callback;


    public WeatherAdapter(AdapterEvent<String > adapterEvent) {
        this.callback = adapterEvent;
        this.weatherList = new ArrayList<>();
    }


    public void addWeather(Weather weather) {
        weatherList.add(weather);
        notifyItemInserted(weatherList.size()-1);
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


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(weatherList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        Weather weather = weatherList.get(position);
        weatherList.remove(weather);
        callback.remove(weather.getName());
        notifyItemRemoved(position);
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
