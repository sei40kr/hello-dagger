package me.yong_ju.hello_dagger;

import dagger.Binds;
import dagger.BindsOptionalOf;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
interface LoginCommandModule {
  @Binds
  @IntoMap
  @StringKey("login")
  Command loginCommand(LoginCommand command);

  @BindsOptionalOf
  Account optionalAccount();
}
