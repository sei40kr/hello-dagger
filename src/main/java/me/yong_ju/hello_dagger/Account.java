package me.yong_ju.hello_dagger;

import java.math.BigDecimal;

class Account {
  private final String username;
  private BigDecimal balance = BigDecimal.ZERO;

  Account(String username) {
    this.username = username;
  }

  String username() {
    return username;
  }

  BigDecimal balance() {
    return balance;
  }

  void deposit(BigDecimal amount) {
    balance = balance.add(amount);
  }

  void withdraw(BigDecimal amount) {
    balance = balance.subtract(amount);
  }
}
