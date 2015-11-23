package sw.es.domain.mapper;

import android.content.res.Resources;

/**
 * Created by alberto on 22/11/15.
 */
public abstract class StringMapper {

    private Resources resources;

    public StringMapper(Resources resources) {
        this.resources = resources;
    }

    public String traslationFor(String name){
        return resFor(resources, name);
    }

    protected abstract String resFor(Resources resources, String name);
    protected abstract String resForDefault(Resources resources);
}
