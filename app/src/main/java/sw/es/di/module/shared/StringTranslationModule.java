package sw.es.di.module.shared;

import android.content.res.Resources;

import dagger.Module;
import dagger.Provides;
import sw.es.domain.mapper.OpenWeatherStringMapper;
import sw.es.domain.mapper.StringMapper;

@Module
public class StringTranslationModule {

    public StringTranslationModule() {}

    @Provides
    StringMapper provideIconMapper(Resources resources){
        return new OpenWeatherStringMapper(resources);
    }

}
