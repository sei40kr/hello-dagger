package me.yong_ju.hello_dagger;

import java.util.List;
import java.util.Optional;

/** Logic to process some user input. */
interface Command {
  /** String token that signifies this command should be selected (e.g.: "deposit", "withdraw") */
  String key();

  /** Process the rest of the command's words and do something. */
  Result handleInput(List<String> input);

  enum Status {
    INVALID,
    HANDLED,
    INPUT_COMPLETED
  }

  class Result {
    private final Status status;
    private final CommandRouter nestedCommandRouter;

    private Result(Status status) {
      this.status = status;
      this.nestedCommandRouter = null;
    }

    private Result(Status status, CommandRouter nestedCommandRouter) {
      this.status = status;
      this.nestedCommandRouter = nestedCommandRouter;
    }

    static Result invalid() {
      return new Result(Status.INVALID);
    }

    static Result handled() {
      return new Result(Status.HANDLED);
    }

    static Result inputCompleted() {
      return new Result(Status.INPUT_COMPLETED);
    }

    static Result enterNestedCommandSet(CommandRouter nestedCommandRouter) {
      return new Result(Status.HANDLED, nestedCommandRouter);
    }

    Status status() {
      return status;
    }

    Optional<CommandRouter> nestedCommandRouter() {
      return Optional.ofNullable(nestedCommandRouter);
    }
  }
}
