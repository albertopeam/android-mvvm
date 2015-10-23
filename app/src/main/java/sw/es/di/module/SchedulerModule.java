package sw.es.di.module;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sw.es.di.common.Named;

@Module
public class SchedulerModule {


    public static final String PUBLISH = "publish";
    public static final String EXECUTION = "execution";


    public SchedulerModule() {}


    @Provides
    @Named(EXECUTION)
    Scheduler provideSchedulerExecution() {
        return Schedulers.io();
    }


    @Provides
    @Named(PUBLISH)
    Scheduler provideSchedulerPublish() {
        return AndroidSchedulers.mainThread();
    }
}
