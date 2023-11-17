import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        IO mIO = new IO();
        Sheet sheet = new Sheet();

        //Test Data
        sheet.editCell(22,6, "Data22_6");
        sheet.editCell(1, 1, "Data1_1");
        sheet.editCell(1, 3, "Data1_3");
        sheet.editCell(2, 2, "Data2_2");
        sheet.editCell(3, 4, "Data3_4");
        sheet.editCell(20, 2, "Data20_2");
        //should be ignored by editCell
        sheet.editCell(22, 3, "");
        sheet.editCell(5, 3, " ");

        //Saving to file
        try {
            mIO.saveToCSV(sheet, "test.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Test Loading to Sheet
        sheet = new Sheet();
        try {
            mIO.loadFromCSV(sheet, "test.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Print read data for verification
        for (CellData cD : sheet) {
            System.out.println("row: " + cD.row + " column: " + cD.column + " value: " + cD.value);
        }
    }
}