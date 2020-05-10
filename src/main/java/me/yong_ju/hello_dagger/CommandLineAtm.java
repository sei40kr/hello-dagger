package me.yong_ju.hello_dagger;

import java.util.Scanner;

class CommandLineAtm {
  public static void main(String[] args) {
    var scanner = new Scanner(System.in);
    var commandProcessor = DaggerCommandProcessorFactory.create().processor();

    while (scanner.hasNextLine()) {
      commandProcessor.process(scanner.nextLine());
    }

    scanner.close();
  }
}
