package ParserHandling.LibraryHandling;

import SheetHandling.Sheet;
import parser.AbstractFactory;
import parser.ExpressionBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Sheet sheet = Sheet.getInstance();
        sheet.editCell(1,2, "23+C3");
        sheet.editCell(1,1, "B1+B2");
        sheet.editCell(2,2, "Hallo");
        System.out.println("Expression: 1+2");

        AbstractFactory factory = new MyFactory();
        ExpressionBuilder builder = new ExpressionBuilder(factory);

        //builder.buildExpression("MAX(A1;3)");
       // builder.buildExpression("A1+C2+D2");
        builder.buildExpression("MAX(1;2)");
        //builder.buildExpression("=MAX(1;2)");

        List<String> references = builder.getCellReferences();

        for(String s : references) {
            System.out.println(s);
        }

        MyNode expression = (MyNode) builder.getExpression();


        System.out.println("Expression= " + expression.getValue() );
        System.out.println("Expression= " + expression.toString());
        /*
        // Returns the list of cell references... in the case empty
        //List<String> references = builder.getCellReferences();

        System.out.println("Done!");

 */
    }
}
