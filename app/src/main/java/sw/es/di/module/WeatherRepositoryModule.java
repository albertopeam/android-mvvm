/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sw.es.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import sw.es.model.local.Weather;
import sw.es.model.repository.outdate.Outdate;
import sw.es.model.repository.usecase.PullUseCase;
import sw.es.model.repository.weather.repo.CloudWeatherDataStore;
import sw.es.model.repository.weather.repo.DBWeatherDataStore;
import sw.es.model.repository.weather.repo.WeatherDataStoreFactory;
import sw.es.model.repository.weather.repo.WeatherOutdate;
import sw.es.model.repository.weather.repo.WeatherRepository;
import sw.es.model.repository.weather.usecase.WeatherPullUseCase;
import sw.es.model.sharedprefs.AppShared;
import sw.es.network.WeatherBackendAPI;

@Module
public class WeatherRepositoryModule {

    public WeatherRepositoryModule() {}

    @Provides
    @Singleton
    WeatherPullUseCase providePullUseCase(WeatherRepository weatherRepository){
        return new WeatherPullUseCase(weatherRepository);
    }

    el scheduler del repo debe ser el main. tiene que poder anotarse o algo

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(WeatherDataStoreFactory weatherDataStoreFactory, Scheduler scheduler){
        return new WeatherRepository(weatherDataStoreFactory, scheduler);
    }


    @Provides
    @Singleton
    WeatherDataStoreFactory provideWeatherDataStoreFactory(CloudWeatherDataStore cloudWeatherDataStore, DBWeatherDataStore dbWeatherDataStore, WeatherOutdate weatherOutdate){
        return new WeatherDataStoreFactory(cloudWeatherDataStore, dbWeatherDataStore, weatherOutdate);
    }


    @Provides
    @Singleton
    CloudWeatherDataStore provideCloudWeatherDataStore(WeatherBackendAPI weatherBackendAPI, Scheduler scheduler){
        return new CloudWeatherDataStore(weatherBackendAPI, scheduler);
    }

    @Provides
    @Singleton
    DBWeatherDataStore provideDbWeatherDataStore(Scheduler scheduler){
        return new DBWeatherDataStore(scheduler);
    }

    @Provides
    @Singleton
    WeatherOutdate provideOutDate(AppShared appShared){
        int minutesBeforeExpire = 60;
        return new WeatherOutdate(appShared, minutesBeforeExpire);
    }
}
