package SheetHandling;

import SheetHandling.CellDataTypes.*;
import SheetHandling.Iterator.CellDataIterator;

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

    public void editCell2(Coordinate coordinate, Content InputContent) {
        //Empty input! skip entry and if cell exists remove it
        if(InputContent.mType == ContentType.EMPTY) {
            if(containsCell2(coordinate)) {
                mCells.get(coordinate.row).remove(coordinate.column);
            }
            return;
        }
        mCells.putIfAbsent(coordinate.row, new TreeMap<>());
        mCells.get(coordinate.row).put(coordinate.column, new Cell(InputContent));
    }

    public Optional<String> getCellContent(Integer row, Integer column) {
        if(!containsCell(row,column)) {
            return Optional.empty();
        }
        return mCells.get(row).get(column).getContent();
    }

    public Content getCellContent2(Coordinate coordinate) {
        if(!containsCell2(coordinate)) {
            return new Content();
        }
        return mCells.get(coordinate.row).get(coordinate.column).getContent2();
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

    public boolean containsCell2(Coordinate coordinate) {
        if (!mCells.containsKey(coordinate.row)) {
            return false;
        }
        if (!mCells.get(coordinate.row).containsKey(coordinate.column)) {
            return false;
        }
        return true;
    }

    public void clear() {
        mCells.clear();
    }

    public List<Coordinate> getRefernceList(Coordinate coordinate) {
        if(!containsCell(coordinate.row, coordinate.column))
        {
            return null;
        }
        return (mCells.get(coordinate.row).get(coordinate.column)).getReferenceList();
    }

    @Override
    public Iterator<CellData> iterator() {
        return new CellDataIterator(mCells);
    }
}