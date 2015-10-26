package sw.es.di.module;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.schedulers.Schedulers;

@Module
public class SchedulerModule {

    public SchedulerModule() {}

    @Provides
    Scheduler provideScheduler(){
        return Schedulers.io();
    }



}
