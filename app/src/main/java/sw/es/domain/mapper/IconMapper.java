package sw.es.domain.mapper;

import android.content.res.Resources;

/**
 * Created by albertopenasamor on 19/11/15.
 */
public abstract class IconMapper {

    private Resources resources;

    public IconMapper(Resources resources) {
        this.resources = resources;
    }

    public int resourceFor(String iconCode){
        return resFor(resources, iconCode);
    }

    protected abstract int resFor(Resources resources, String iconCode);
    protected abstract int resForDefault(Resources resources);
}
