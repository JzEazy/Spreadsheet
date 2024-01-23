package ParserHandling.DataTypes;

import SheetHandling.CellDataTypes.Content;
import SheetHandling.CellDataTypes.ContentType;
import SheetHandling.CellDataTypes.Coordinate;

import java.util.Optional;

public class ParserResult {
    public Coordinate mCoordinate;
    public Content mContent;
    public Optional<String> Error = Optional.empty();

    public ParserResult(String Error) {
        this.Error = Optional.of(Error);
    }

    public ParserResult(Coordinate mCoordinate, Content mContent) {
        this.mCoordinate = mCoordinate;
        this.mContent = mContent;
    }

    public ParserResult() {
    }

    public void setError(String Error) {
        this.Error = Optional.of(Error);
    }
    public void setContentViaType(ContentType type, String content) {
        mContent = new Content();
        if(type == ContentType.EMPTY) {
            mContent.setEmpty();
        } else if (type == ContentType.TEXT) {
            mContent.setText(content);
        } else if (type == ContentType.NUMERICAL) {
            mContent.setNumerical(content);
        } else if (type == ContentType.FORMULA) {
            mContent.setFormula(content);
        }
    }
    public void setContentAsEmpty() {
        mContent.setEmpty();
    }
}
