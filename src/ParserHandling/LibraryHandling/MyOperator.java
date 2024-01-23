package ParserHandling.LibraryHandling;

import parser.AbstractNode;

public class MyOperator implements MyNode {
    protected char op;
    private MyNode left;
    private MyNode right;

    public MyOperator(char op, MyNode left, MyNode right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return "OP: "+op;
    }

    public AbstractNode getLeft() {
        return left;
    }

    public AbstractNode getRight() {
        return right;
    }
    @Override
    public double getValue() {
        switch (op) {
            case '+':
                return left.getValue() + right.getValue();
            case '-':
                return left.getValue() - right.getValue();
            case '*':
                return left.getValue() * right.getValue();
            case '/':
                return left.getValue() / right.getValue();
            default:
        }
        //should never be returned
        return 0;
    }
}
