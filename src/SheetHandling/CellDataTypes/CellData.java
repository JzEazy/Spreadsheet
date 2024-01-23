package CellDataTypes;

import java.util.Optional;

//Used to display DataTypes.Cell data and metadata when iterating over Cells in SheetLogic.Sheet
public class CellData {
    public Integer row;
    public Integer column;
    public Optional<String> value;

    public Coordinate mCoordinate;
    public Content mContent;

    public CellData(Integer row, Integer column, Cell cell) {
        this.row = row;
        this.column = column;
        this.value = cell.getContent();
    }
}