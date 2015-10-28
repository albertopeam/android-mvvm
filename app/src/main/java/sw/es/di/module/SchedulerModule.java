package sw.es.di.module;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sw.es.di.common.ExecutionScheduler;
import sw.es.di.common.ListenScheduler;

@Module
public class SchedulerModule {

    public SchedulerModule() {}

    @Provides
    @ExecutionScheduler
    Scheduler provideExecutionScheduler(){
        return Schedulers.io();
    }


    @Provides
    @ListenScheduler
    Scheduler provideListenScheduler(){
        return AndroidSchedulers.mainThread();
    }

}
