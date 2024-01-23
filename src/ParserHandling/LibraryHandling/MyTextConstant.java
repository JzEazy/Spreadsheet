package ParserHandling.LibraryHandling;

import parser.AbstractNode;

public class MyTextConstant implements AbstractNode {
    protected String value;

    public MyTextConstant(String text) {
        this.value = text;
    }

    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }
}
