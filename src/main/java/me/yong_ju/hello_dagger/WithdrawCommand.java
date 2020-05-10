package me.yong_ju.hello_dagger;

import java.math.BigDecimal;
import javax.inject.Inject;

final class WithdrawCommand extends BigDecimalCommand {
  private final Account account;
  private final BigDecimal minimumBalance;
  private final WithdrawalLimiter withdrawalLimiter;

  @Inject
  protected WithdrawCommand(
      Outputter outputter,
      Account account,
      @MinimumBalance BigDecimal minimumBalance,
      WithdrawalLimiter withdrawalLimiter) {
    super(outputter);
    this.account = account;
    this.minimumBalance = minimumBalance;
    this.withdrawalLimiter = withdrawalLimiter;
  }

  @Override
  protected void handleAmount(BigDecimal amount) {
    var remainingWithdrawalLimit = withdrawalLimiter.remainingWithdrawalLimit();
    if (amount.compareTo(remainingWithdrawalLimit) > 0) {
      // TODO output error
      outputter.output(
          String.format(
              "you may not withdraw %s; you may withdraw %s more in this session",
              amount, remainingWithdrawalLimit));
      return;
    }

    var newBalance = account.balance().subtract(amount);
    if (newBalance.compareTo(minimumBalance) < 0) {
      // TODO output error
    } else {
      account.withdraw(amount);
      withdrawalLimiter.recordWithdrawal(amount);
      outputter.output("your new balance is: " + account.balance());
    }
  }

  @Override
  public String key() {
    return "withdraw";
  }
}
