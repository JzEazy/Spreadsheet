import javax.swing.*;

import UI.ConsoleUI;
import UI.SpreadsheetGUI;

public class Main {
    public static void main(String[] args) {

        ConsoleUI mConsoleUI = new ConsoleUI();
        while(!mConsoleUI.mEndProgram) {
            mConsoleUI.InputHandler();
        }

        //Todo implement full GUI till 26.01
        SwingUtilities.invokeLater(() -> {
            SpreadsheetGUI gui = new SpreadsheetGUI();
            gui.show();
        });
    }
}