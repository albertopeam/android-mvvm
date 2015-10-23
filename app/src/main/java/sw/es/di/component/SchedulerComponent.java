package sw.es.di.component;

import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;
import sw.es.di.common.Named;
import sw.es.di.module.SchedulerModule;

/**
 * Created by albertopenasamor on 22/10/15.
 */
@Singleton
@Component(
        modules = {
                SchedulerModule.class
        }
)
public interface SchedulerComponent {
    @Named(SchedulerModule.EXECUTION) Scheduler provideSchedulerExecution();
    @Named(SchedulerModule.PUBLISH) Scheduler provideSchedulerPublish();
}
