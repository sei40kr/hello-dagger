package me.yong_ju.hello_dagger;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import me.yong_ju.hello_dagger.Command.Result;
import me.yong_ju.hello_dagger.Command.Status;

final class CommandRouter {
  private final Map<String, Command> commands;

  @Inject
  CommandRouter(Map<String, Command> commands) {
    // This map contains:
    // "hello" -> HelloWorldCommand
    // "login" -> LoginCommand
    this.commands = commands;
  }

  private static List<String> split(String string) {
    return Arrays.asList(string.split("\\s+"));
  }

  Result route(String input) {
    var splitInput = split(input);
    if (splitInput.isEmpty()) {
      return invalidCommand(input);
    }

    var commandKey = splitInput.get(0);
    var command = commands.get(commandKey);
    if (command == null) {
      return invalidCommand(input);
    }

    var result = command.handleInput(splitInput.subList(1, splitInput.size()));
    if (result.status() == Status.INVALID) {
      System.out.println(commandKey + ": invalid arguments");
    }
    return result;
  }

  private Result invalidCommand(String input) {
    System.out.println(String.format("couldn't understand \"%s\". please try again.", input));
    return Result.invalid();
  }
}
