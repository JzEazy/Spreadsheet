import java.util.Optional;

public class CellData {
    public Integer row;
    public Integer column;
    public Cell cell;

    public Optional<String> value;

    public CellData(Integer row, Integer column, Cell cell) {
        this.row = row;
        this.column = column;
        this.cell = cell;
        this.value = Optional.ofNullable(cell.getContent());
    }
}