package me.yong_ju.hello_dagger;

import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(
    modules = {
      HelloWorldModule.class,
      LoginCommandModule.class,
      UserCommandsRouter.InstallationModule.class,
      SystemOutModule.class
    })
interface CommandProcessorFactory {
  CommandProcessor processor();
}
