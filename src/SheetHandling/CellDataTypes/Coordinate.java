package SheetHandling.CellDataTypes;

//Stores cell coordinate as row and column Integer
//Acts as an Adapter between String and Integer coordinate representation
public class Coordinate {
    public Integer row;
    public Integer column;

    public Coordinate(Integer row, Integer column) {
        this.column = column;
        this.row = row;
    }
    public Coordinate(String coordinate) {
        this.column = convertColumnStringToNumber(coordinate.replaceAll("[0-9]", ""));
        this.row = Integer.parseInt(coordinate.replaceAll("[^0-9]", ""));
    }

    // Converts column string to a number (e.g., A=1, B=2, ..., AA=27)
    private int convertColumnStringToNumber(String columnString) {
        int number = 0;
        for (int i = 0; i < columnString.length(); i++) {
            number *= 26;
            number += columnString.charAt(i) - 'A' + 1;
        }
        return number;
    }

    // Converts the internal row and column back to the string format
    @Override
    public String toString() {
        return convertColumnNumberToString(column) + row;
    }

    // Converts column number back to string (e.g., 1=A, 2=B, ..., 27=AA)
    private String convertColumnNumberToString(int column) {
        StringBuilder columnName = new StringBuilder();
        while (column > 0) {
            int remainder = (column - 1) % 26;
            columnName.insert(0, (char) ('A' + remainder));
            column = (column - remainder) / 26;
        }
        return columnName.toString();
    }
}
