package me.yong_ju.hello_dagger;

import java.util.Optional;
import javax.inject.Inject;

final class LoginCommand extends SingleArgCommand {
  private final Database database;
  private final Outputter outputter;
  private final UserCommandsRouter.Factory userCommandsRouterFactory;
  private final Optional<Account> account;

  @Inject
  LoginCommand(
      Database database,
      Outputter outputter,
      UserCommandsRouter.Factory userCommandsRouterFactory,
      Optional<Account> account) {
    this.database = database;
    this.outputter = outputter;
    this.userCommandsRouterFactory = userCommandsRouterFactory;
    this.account = account;
  }

  @Override
  public String key() {
    return "login";
  }

  @Override
  protected Result handleArg(String username) {
    if (account.isPresent()) {
      // Ignore "login <foo>" commands if we already have an account
      return Result.handled();
    }

    Account account = database.getAccount(username);
    outputter.output(account.username() + " is logged in with balance " + account.balance() + ".");
    return Result.enterNestedCommandSet(userCommandsRouterFactory.create(account).router());
  }
}
