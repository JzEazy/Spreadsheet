package SheetHandling.CellDataTypes;

import java.util.List;
import java.util.Optional;

public class Cell {
    public Content mCContent;
    private Optional<String> mContent;
    private List<Coordinate> mReferences;
    public Cell() { this.mContent = Optional.empty(); }

    public Cell(String content) {
        this.mContent = Optional.of(content);
    }

    public Cell(Content mCContent) {
        this.mCContent = mCContent;
    }

    public void setContent(String content) { this.mContent = Optional.of(content);}

    public Optional<String> getContent() { return mContent;}

    public Content getContent2() { return mCContent;}

    public String evaluatedContent() {return mCContent.getContent();}

    public List<Coordinate> getReferenceList() {
        return mReferences;
    }

}