package me.yong_ju.hello_dagger;

import java.util.List;
import javax.inject.Inject;

final class HelloWorldCommand implements Command {
  private final Outputter outputter;

  @Inject
  HelloWorldCommand(Outputter outputter) {
    this.outputter = outputter;
  }

  @Override
  public String key() {
    return "hello";
  }

  @Override
  public Result handleInput(List<String> input) {
    outputter.output("world!");
    return Result.handled();
  }
}
