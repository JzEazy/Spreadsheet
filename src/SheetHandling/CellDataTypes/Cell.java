package CellDataTypes;

import java.util.List;
import java.util.Optional;

public class Cell {
    private Content mCContent;
    private Optional<String> mContent;
    private List<Coordinate> mReferences;
    public Cell() { this.mContent = Optional.empty(); }

    public Cell(String content) {
        this.mContent = Optional.of(content);
    }

    public void setContent(String content) { this.mContent = Optional.of(content);}

    public Optional<String> getContent() { return mContent;}

}