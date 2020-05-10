package me.yong_ju.hello_dagger;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
final class Database {
  private final Map<String, Account> accounts = new HashMap<>();

  @Inject
  Database() {}

  Account getAccount(String username) {
    return accounts.computeIfAbsent(username, Account::new);
  }
}
