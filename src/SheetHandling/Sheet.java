package SheetLogic;

import CellDataTypes.Cell;
import CellDataTypes.CellData;

import java.util.*;

public class Sheet implements Iterable<CellData> {

    // Singleton instance of the class
    private static Sheet instance;
    public TreeMap<Integer, TreeMap<Integer, Cell>> mCells;

    private Sheet() {
        mCells = new TreeMap<>();
    }

    // Static method to get the single instance of the class
    public static Sheet getInstance() {
        if (instance == null) {
            instance = new Sheet();
        }
        return instance;
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
        mCells.get(row).put(column, new Cell(content));
    }

    public Optional<String> getCellContent(Integer row, Integer column) {
        if(!containsCell(row,column)) {
            return Optional.empty();
        }
        return mCells.get(row).get(column).getContent();
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

    public void clear() {
        mCells.clear();
    }

    @Override
    public Iterator<CellData> iterator() {
        return new CellDataIterator(mCells);
    }
}