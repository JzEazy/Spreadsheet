package ParserHandling.LibraryHandling;

import parser.AbstractNode;

public class MyError implements AbstractNode {
    protected String detail;

    public MyError(String detail) {
        this.detail = detail;
    }

    public String toString() {
        return "ERROR: "+ detail;
    }

}
