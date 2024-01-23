package SheetHandling.Iterator;

import SheetHandling.CellDataTypes.Cell;
import SheetHandling.CellDataTypes.CellData;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class CellDataIterator implements Iterator<CellData> {
    private Iterator<Map.Entry<Integer, TreeMap<Integer, Cell>>> rowIterator;
    private Iterator<Map.Entry<Integer, Cell>> cellIterator;
    private int currentRow;
    private CellData nextCellData;

    public CellDataIterator(TreeMap<Integer, TreeMap<Integer, Cell>> cells) {
        this.rowIterator = cells.entrySet().iterator();
        this.cellIterator = null;
        this.nextCellData = null;
        advance();
    }

    private void advance() {
        while ((cellIterator == null || !cellIterator.hasNext()) && rowIterator.hasNext()) {
            Map.Entry<Integer, TreeMap<Integer, Cell>> rowEntry = rowIterator.next();
            currentRow = rowEntry.getKey();
            cellIterator = rowEntry.getValue().entrySet().iterator();
        }
        if (cellIterator != null && cellIterator.hasNext()) {
            Map.Entry<Integer, Cell> cellEntry = cellIterator.next();
            //nextCellData = new CellData(currentRow, cellEntry.getKey(), cellEntry.getValue());
            nextCellData = new CellData(currentRow, cellEntry.getKey(), cellEntry.getValue().getContent2());
        } else {
            nextCellData = null;
        }
    }

    @Override
    public boolean hasNext() {
        return nextCellData != null;
    }

    @Override
    public CellData next() {
        if (nextCellData == null) {
            throw new NoSuchElementException();
        }
        CellData current = nextCellData;
        advance();
        return current;
    }
}
