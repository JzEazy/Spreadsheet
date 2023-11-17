import java.io.*;

public class IO {
    public void saveToCSV(Sheet sheet, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            StringBuilder lineBuilder = new StringBuilder();
            Integer lastRow = 1;
            Integer lastCol = 1;
            for (CellData cD : sheet) {
                // Handle new rows
                if(cD.row > lastRow) {
                    writer.write(lineBuilder.toString());
                    //Insert newlines
                    while (cD.row > lastRow) {
                        writer.newLine();
                        lastRow++;
                    }
                    lastCol = 1;
                    lineBuilder = new StringBuilder();
                }
                //Handle ; between columns
                while (cD.column > lastCol) {
                    lineBuilder.append(";");
                    lastCol++;
                }
                //Write cell value if it exists
                lineBuilder.append(cD.value.orElse(""));
                lastRow = cD.row;
                lastCol = cD.column;
            }
            // Write the last line if not empty
            if (lineBuilder.length() > 0) {
                writer.write(lineBuilder.toString());
            }
        }
    }

    public Sheet loadFromCSV(Sheet sheet, String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int row = 1;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) { // Only process non-empty lines
                    String[] cellContents = line.split(";", -1);
                    for (int col = 0; col < cellContents.length; col++) {
                        if (!cellContents[col].isEmpty()) {
                            sheet.editCell(row, col + 1, cellContents[col]);
                        }
                    }
                }
                row++;
            }
        }

        return sheet;
    }
}
