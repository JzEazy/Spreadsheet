package SheetHandling;

import SheetHandling.CellDataTypes.CellData;
import SheetHandling.CellDataTypes.Content;
import SheetHandling.CellDataTypes.Coordinate;

import java.util.List;
import java.util.Optional;

public class SheetHandler {

    private Sheet mSheet;
    private IO mIO;

    public SheetHandler() {
        this.mSheet = Sheet.getInstance();
        this.mIO = new IO();
    }

    public void editCell(Integer row, Integer column, String InputContent) {
        mSheet.editCell(row, column, InputContent);
    }

    public void editCell2(Coordinate coordinate, Content content) {
        mSheet.editCell2(coordinate, content);
    }

    public Content getCellContent(Coordinate coordinate) {
        return mSheet.getCellContent2(coordinate);
    }

    public boolean containsCell(Integer row, Integer column) {
        return mSheet.containsCell(row, column);
    }

    public void clear() {
        mSheet.clear();
    }

    public boolean saveToCSV(String filename) {
        return mIO.saveToCSV(filename);
    }

    public boolean loadFromCSV(String filename) {
        return mIO.loadFromCSV(filename);
    }

    public List<Coordinate> getReferenceList(Coordinate coordinate) {return mSheet.getRefernceList(coordinate);}

    public void print()
    {
        for (CellData cD : mSheet) {
            Coordinate coordinate = new Coordinate(cD.row,cD.column);
            System.out.println("[" + coordinate.toString() + "]" + " = " + cD.mContent.getContent());
        }
    }

}
