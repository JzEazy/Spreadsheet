package ParserHandling;

import ParserHandling.DataTypes.ParserResult;
import SheetHandling.CellDataTypes.Content;
import SheetHandling.CellDataTypes.ContentType;
import SheetHandling.CellDataTypes.Coordinate;
import ParserHandling.LibraryHandling.MyFactory;
import ParserHandling.LibraryHandling.MyNode;
import parser.AbstractFactory;
import parser.ExpressionBuilder;

import java.util.ArrayList;
import java.util.List;

public class ParseHandler {
    private Classifier mClassifier;
    private CircularReferenceCheck mCRC;
    private TextInputCheck mTextInputCheck;
    private AbstractFactory factory;
    private ExpressionBuilder builder;

    public ParseHandler() {
        this.mClassifier = new Classifier();
        this.mCRC = new CircularReferenceCheck();
        this.mTextInputCheck = new TextInputCheck();
        this.factory = new MyFactory();
        this.builder = new ExpressionBuilder(this.factory);
    }

    public ParserResult parse(String coordinate, String Content) {
        ParserResult parserResult = new ParserResult();
        //check coordinate validitate
        if(!mTextInputCheck.isValidCoordinate(coordinate)) {
            parserResult.setError("Error: Invalid coordinates!");
            return parserResult;
        }
        parserResult.mCoordinate = new Coordinate(coordinate);
        //check Content
        ContentType type = mClassifier.classify(Content);
        //Handle TEXT, NUMMERICAL, EMPTY
        if(type != ContentType.FORMULA) {
            parserResult.setContentViaType(type,Content);
            return parserResult;
        }
        //Else Handle FORMULA
        builder.buildExpression(Content);
        MyNode expression = (MyNode) builder.getExpression();

        //Convert String references to coordinates
        List<String> references = builder.getCellReferences();
        List <Coordinate> CoordinatReferences = new ArrayList<>();
        for(String ref : references) {
            CoordinatReferences.add(new Coordinate(ref));
        }
        //Check for possible Circular dependency
        if(mCRC.isDependent( parserResult.mCoordinate,CoordinatReferences)) {
            parserResult.setError("Error: Circular dependency detected!");
            return parserResult;
        }
        parserResult.setContentViaType(type,Double.toString(expression.getValue()));
        return parserResult;
    }

    public boolean VerifyCoordinate(String Coordinate) {
        return mTextInputCheck.isValidCoordinate(Coordinate);
    }

    public ContentType classify(String input) {
        return mClassifier.classify(input);
    }

}
