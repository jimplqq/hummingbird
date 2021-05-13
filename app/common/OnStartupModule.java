package common;

import com.google.inject.AbstractModule;

/**
 * Created by questiny on 17-4-17.
 */
public class OnStartupModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(OnStartUp.class).asEagerSingleton();
    }
}
