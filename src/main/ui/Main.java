package ui;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TerminalUI terminalHandler = new TerminalUI();
        Scanner input = new Scanner(System.in);
        terminalHandler.init(input);
    }
}
