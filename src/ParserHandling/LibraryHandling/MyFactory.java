package ParserHandling.LibraryHandling;

import parser.AbstractFactory;
import parser.AbstractNode;

public class MyFactory implements AbstractFactory {

    @Override
    public AbstractNode buildOperator(char c, AbstractNode left, AbstractNode right) {
       /* switch(c) {

            case '+':
                MySum s = new MySum();
                s.op1 = (myNode)abstractNode;
                s.op2 = (myNode)abstractNode1;
                return s;
            case "-":

        }*/
      return new MyOperator(c, (MyNode) left, (MyNode) right);

    }
    /*@Override
    public AbstractNode buildOperator(char op, AbstractNode left, AbstractNode right) {
        return new Operator(op, left, right);
    }
*/
    @Override
    public AbstractNode buildFunction(String name, AbstractNode[] parameters) {
        return new MyFunctionCall(name, parameters);
    }

    @Override
    public AbstractNode buildNumberConstant(double number) {
        return new MyNumberConstant(number);
    }

    @Override
    public AbstractNode buildTextConstant(String text) {
        return new MyTextConstant(text);
    }

    @Override
    public AbstractNode buildCellReference(String reference) {
        return new MyCellReference(reference);
    }

    @Override
    public AbstractNode buildError(String detail) {
        return new MyError(detail);
    }
}
