package sw.es.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import sw.es.model.local.Weather;

/**
 * Created by albertopenasamor on 15/10/15.
 */
public class WeatherReciclerView extends RecyclerView.Adapter<WeatherReciclerView.WeatherViewHolder> {


    private List<Weather> weatherList;


    public WeatherReciclerView() {
        this.weatherList = Collections.emptyList();
    }


    public void setRepositories(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }


    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO:
        /*ItemRepoBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_repo,
                parent,
                false);
        return new RepositoryViewHolder(binding);*/
        return null;
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        //TODO:
        //holder.bindRepository(repositories.get(position));
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public static class WeatherViewHolder extends RecyclerView.ViewHolder {

        public WeatherViewHolder(View itemView) {
            super(itemView);
        }
        /*
        final ItemRepoBinding binding;

        public RepositoryViewHolder(ItemRepoBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }

        void bindRepository(Repository repository) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new ItemRepoViewModel(itemView.getContext(), repository));
            } else {
                binding.getViewModel().setRepository(repository);
            }
        }
        */
    }
}
