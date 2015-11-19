package sw.es.di.module.shared;

import android.content.res.Resources;

import dagger.Module;
import dagger.Provides;
import sw.es.domain.mapper.IconMapper;
import sw.es.domain.mapper.MaterialDesignIconMapper;

@Module
public class IconModule {

    public IconModule() {}

    @Provides
    IconMapper provideIconMapper(Resources resources){
        return new MaterialDesignIconMapper(resources);
    }

}
