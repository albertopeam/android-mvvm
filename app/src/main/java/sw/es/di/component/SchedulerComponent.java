package sw.es.di.component;

import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;
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
    Scheduler provideSchedulerExecution();
    Scheduler provideSchedulerPublish();
}
