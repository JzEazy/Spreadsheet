package ParserHandling.LibraryHandling;

public class MyNumberConstant implements MyNode {
    protected double value;

    public MyNumberConstant(double n) {
        value = n;
    }

    public String toString() {
        return ""+value;
    }

    public double getValue() {return value;}
}
