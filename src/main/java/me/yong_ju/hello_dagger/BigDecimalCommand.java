package me.yong_ju.hello_dagger;

import java.math.BigDecimal;

abstract class BigDecimalCommand extends SingleArgCommand {
  protected final Outputter outputter;

  protected BigDecimalCommand(Outputter outputter) {
    this.outputter = outputter;
  }

  private static BigDecimal tryParse(String arg) {
    try {
      return new BigDecimal(arg);
    } catch (NumberFormatException e) {
      return null;
    }
  }

  @Override
  protected Result handleArg(String arg) {
    var amount = tryParse(arg);
    if (amount == null) {
      outputter.output(arg + " is not a valid number.");
    } else if (amount.signum() <= 0) {
      outputter.output("amount must be positive");
    } else {
      handleAmount(amount);
    }
    return Result.handled();
  }

  protected abstract void handleAmount(BigDecimal amount);
}
