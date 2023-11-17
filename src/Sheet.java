import java.util.*;

public class Sheet implements Iterable<CellData> {
    public TreeMap<Integer, TreeMap<Integer, Cell>> mCells;

    public Sheet() {
        mCells = new TreeMap<>();
    }

    //Handles all changes to cells
    public void editCell(Integer row, Integer column, String InputContent) {
        //Remove all whitespaces from Input
        String content = InputContent.replaceAll("\\s+", "");
        //Empty input! skip entry and if cell exists remove it
        if(content.isEmpty()) {
            if(containsCell(row,column)) {
                mCells.get(row).remove(column);
            }
            return;
        }
        mCells.putIfAbsent(row, new TreeMap<>());
        mCells.get(row).put(column, new Cell(row, column, content));
    }

    public Cell getCell(Integer row, Integer column) {
        if (!containsCell(row, column)) {
            return null;
        }
        return mCells.get(row).get(column);
    }

    public boolean containsCell(Integer row, Integer column) {
        if (!mCells.containsKey(row)) {
            return false;
        }
        if (!mCells.get(row).containsKey(column)) {
            return false;
        }
        return true;
    }

    public boolean hasValue(Integer row, Integer column) {
        if (!mCells.containsKey(row)) {
            return false;
        }
        if (!mCells.get(row).containsKey(column)) {
            return false;
        }
        return mCells.get(row).get(column).hasValue();
    }

    @Override
    public Iterator<CellData> iterator() {
        return new CellDataIterator(mCells);
    }
}