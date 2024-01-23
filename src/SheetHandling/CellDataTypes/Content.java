package SheetHandling.CellDataTypes;

import java.util.Optional;

public class Content {
    public ContentType mType;
    public String mValue;
    public String mFormula;

    public Content() {
        this.mType = ContentType.EMPTY;
    }

    public Content(ContentType mType, String mValue, String mFormula) {
        this.mType = mType;
        this.mValue = mValue;
        this.mFormula = mFormula;
    }

    public Content(ContentType mType, String mValue) {
        this.mType = mType;
        this.mValue = mValue;
        this.mFormula = "0";
    }

    public String getContent() {
        if(mType == ContentType.FORMULA) {
            return mFormula;
        }
        return mValue;
    }

    public void setText(String text) {
        this.mFormula = "0";
        this.mType = ContentType.TEXT;
        this.mValue = text;
    }

    public void setFormula(String formula) {
        this.mFormula = formula;
        this.mType = ContentType.FORMULA;
        this.mValue = "";
    }

    public void setNumerical(String text) {
        this.mFormula = "0";
        this.mType = ContentType.NUMERICAL;
        this.mValue = text;
    }

    public void setEmpty() {
        this.mFormula = "0";
        this.mType = ContentType.EMPTY;
        this.mValue = "";
    }
}
