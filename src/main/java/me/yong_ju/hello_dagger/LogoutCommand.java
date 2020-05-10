package me.yong_ju.hello_dagger;

import java.util.List;
import javax.inject.Inject;

final class LogoutCommand implements Command {
  @Inject
  LogoutCommand() {}

  @Override
  public String key() {
    return "logout";
  }

  @Override
  public Result handleInput(List<String> input) {
    return input.isEmpty() ? Result.inputCompleted() : Result.invalid();
  }
}
