package sw.es.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sw.es.dagger2.R;
import sw.es.dagger2.databinding.RowAppWidgetConfigBinding;
import sw.es.model.local.FavouriteLocation;

/**
 * Created by albertopenasamor on 15/10/15.
 */
public class AppWidgetConfigAdapter extends RecyclerView.Adapter<AppWidgetConfigAdapter.ConfigViewHolder>{


    private List<FavouriteLocation> favouriteLocationList;


    public AppWidgetConfigAdapter() {
        this.favouriteLocationList = new ArrayList<>();
    }


    public List<FavouriteLocation> getFavouriteLocationList() {
        return favouriteLocationList;
    }

    public void setFavouriteLocationList(List<FavouriteLocation> favouriteLocationList) {
        this.favouriteLocationList = favouriteLocationList;
        if (this.favouriteLocationList == null){
            this.favouriteLocationList = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    @Override
    public ConfigViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_app_widget_config, parent, false);
        ConfigViewHolder configViewHolder = new ConfigViewHolder(view);
        return configViewHolder;
    }


    @Override
    public void onBindViewHolder(ConfigViewHolder holder, int position) {
        final FavouriteLocation favouriteLocation = favouriteLocationList.get(position);
        holder.getBinding().setFavLocation(favouriteLocation);
        holder.getBinding().executePendingBindings();
    }


    @Override
    public int getItemCount() {
        return favouriteLocationList.size();
    }


    public static class ConfigViewHolder extends RecyclerView.ViewHolder {

        final private RowAppWidgetConfigBinding binding;

        public ConfigViewHolder(View view) {
            super(view);
            this.binding = DataBindingUtil.bind(view);
        }


        public RowAppWidgetConfigBinding getBinding() {
            return binding;
        }
    }
}
