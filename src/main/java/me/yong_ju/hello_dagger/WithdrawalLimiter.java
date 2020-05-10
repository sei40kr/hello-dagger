package me.yong_ju.hello_dagger;

import java.math.BigDecimal;
import javax.inject.Inject;

@PerSession
final class WithdrawalLimiter {
  private BigDecimal remainingWithdrawalLimit;

  @Inject
  WithdrawalLimiter(@MaximumWithdrawal BigDecimal maximumWithdrawal) {
    this.remainingWithdrawalLimit = maximumWithdrawal;
  }

  BigDecimal remainingWithdrawalLimit() {
    return remainingWithdrawalLimit;
  }

  void recordDeposit(BigDecimal amount) {}

  void recordWithdrawal(BigDecimal amount) {
    remainingWithdrawalLimit = remainingWithdrawalLimit.subtract(amount);
  }
}
