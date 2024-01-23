package ParserHandling.LibraryHandling;
import SheetHandling.CellDataTypes.Content;
import SheetHandling.CellDataTypes.ContentType;
import SheetHandling.CellDataTypes.Coordinate;
import SheetHandling.Sheet;

import SheetHandling.SheetHandler;
import parser.AbstractFactory;
import parser.ExpressionBuilder;

public class MyCellReference implements MyNode {
    protected String reference;
    protected SheetHandler mSheetHandler;

    public MyCellReference(String text) {
        this.reference = text;
        this.mSheetHandler = new SheetHandler();
    }

    public String toString() {
        return "DataTypes.Cell: "+ reference;
    }

    public double getValue() {
        Content content = mSheetHandler.getCellContent(new Coordinate(reference));
        if(content.mType == ContentType.TEXT) {
            //Throw invalid text
        } else if (content.mType == ContentType.EMPTY) {
            return 0;
        } else if (content.mType == ContentType.NUMERICAL) {
            return Double.valueOf(content.mValue);
        }
        //Else Content is Formula
        AbstractFactory factory = new MyFactory();
        ExpressionBuilder builder = new ExpressionBuilder(factory);
        builder.buildExpression(content.mFormula);
        MyNode expression = (MyNode) builder.getExpression();
        return expression.getValue();
    }
}


