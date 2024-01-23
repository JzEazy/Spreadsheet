package UI;

import ParserHandling.DataTypes.ParserResult;
import SheetHandling.CellDataTypes.Content;
import SheetHandling.CellDataTypes.Coordinate;
import SheetHandling.SheetHandler;
import ParserHandling.ParseHandler;
import java.io.File;
import java.util.Scanner;

public class ConsoleUI {
    public boolean mEndProgram;
    private SheetHandler mSheetHandler;
    private ParseHandler mParserHandler;
    private Scanner scanner;

    public ConsoleUI() {
        this.mEndProgram = false;
        this.mSheetHandler = new SheetHandler();
        this.mParserHandler = new ParseHandler();
        scanner = new Scanner(System.in);
    }

    public void InputHandler() {
        while (!mEndProgram) {
            System.out.println("Choose an option: \n1. Create New Spreadsheet \n2. Load Spreadsheet \n3. Enter Cell Data \n4. Save Spreadsheet \n5. Print current Spreadsheet to console. \n6. Exit");
            int choice = getIntInput();
            switch (choice) {
                case 1:
                    createNewSpreadsheet();
                    break;
                case 2:
                    loadSpreadsheet();
                    break;
                case 3:
                    enterCellData();
                    break;
                case 4:
                    saveSpreadsheet();
                    break;
                case 5:
                    printSpreadsheetToConsol();
                    break;
                case 6:
                    mEndProgram = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            clearScreen();
        }
    }

    private void createNewSpreadsheet() {
        mSheetHandler.clear();
        System.out.println("New spreadsheet created.");
    }

    private void loadSpreadsheet() {
        boolean validInput = false;
        String path = "";
        while(!validInput) {
            System.out.print("Enter file path to load: ");
            path = scanner.nextLine();
            validInput = mSheetHandler.loadFromCSV(path);
            inputValidityMessage(validInput);
        }
        System.out.println("Spreadsheet loaded from " + path);
    }

    private void enterCellData() {
        boolean validInput = false;
        String coordinate = "";
        ParserResult result = new ParserResult();

        while (!validInput) {
            System.out.print("Enter cell coordinate (e.g., A1): ");
            coordinate = scanner.nextLine();
            System.out.print("Enter cell content: ");
            String content = scanner.nextLine();
            result = mParserHandler.parse(coordinate, content);
            if (result.Error.isPresent()) {
                System.out.println(result.Error.get());
                inputValidityMessage(false);
                continue;
            }
            validInput = true;
            System.out.println("Data entered into cell " + coordinate);
        }
        mSheetHandler.editCell2(result.mCoordinate, result.mContent);
    }

    private void saveSpreadsheet() {
        boolean validInput = false;
        String path = "";
        while (!validInput) {
            System.out.print("Enter file path to save: ");
            path = scanner.nextLine();
            validInput = mSheetHandler.saveToCSV(path);
            inputValidityMessage(validInput);
        }
        System.out.println("Spreadsheet saved to " + path);
    }

    private void inputValidityMessage(boolean input)
    {
        if(input) {
            System.out.println("Valid input!");
        }
        else {
            System.out.println("Invalid input - try again!");
        }
    }

    private void printSpreadsheetToConsol() {
        mSheetHandler.print();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
    }

    private int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear the invalid input
        }
        int number = scanner.nextInt();
        scanner.nextLine();  // Clear the newline character after the number
        return number;
    }

}
