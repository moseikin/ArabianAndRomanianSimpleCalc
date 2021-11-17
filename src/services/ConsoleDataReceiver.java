package services;

import interfaces.DataReceiver;

import java.util.Scanner;

public class ConsoleDataReceiver implements DataReceiver {
    private final StringParsing stringParsing = new StringParsing();

    @Override
    public void receive() throws Exception {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(" ");
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("q")) {
                break;
            }
            stringParsing.input(input);
        }
        scanner.close();
    }
}
