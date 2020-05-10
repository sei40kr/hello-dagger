package me.yong_ju.hello_dagger;

import dagger.BindsInstance;
import dagger.Module;
import dagger.Subcomponent;

@PerSession
@Subcomponent(modules = {AmountsModule.class, UserCommandsModule.class})
interface UserCommandsRouter {
  CommandRouter router();

  @Subcomponent.Factory
  interface Factory {
    UserCommandsRouter create(@BindsInstance Account account);
  }

  @Module(subcomponents = UserCommandsRouter.class)
  interface InstallationModule {}
}
