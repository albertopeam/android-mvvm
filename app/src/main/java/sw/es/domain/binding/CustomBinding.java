package sw.es.domain.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by phanirajabhandari on 7/8/15.
 */
public class CustomBinding {

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String icon) {
        String url = String.format("http://openweathermap.org/img/w/%s.png", icon);
        Glide.with(imageView.getContext()).load(url).crossFade().into(imageView);
    }
}
