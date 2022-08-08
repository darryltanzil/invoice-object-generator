package ui;

import java.util.Scanner;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        TerminalUI terminalHandler = new TerminalUI();
        SwingUI guiHandler = new SwingUI(terminalHandler);
        Scanner input = new Scanner(System.in);
        terminalHandler.init(input);
    }
}
