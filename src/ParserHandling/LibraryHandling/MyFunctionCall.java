package ParserHandling.LibraryHandling;

import parser.AbstractNode;

public class MyFunctionCall implements MyNode {
    protected String name;
    protected AbstractNode[] parameters;

    public MyFunctionCall(String name, AbstractNode[] parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public String toString() {
        return "FUNCTION: "+name;
    }

    @Override
    public double getValue() {
        if(parameters.length == 0)
        {
            return 0;
        }
        double returnValue = 0;
        switch (name) {
            case "SUMMA":
                for(AbstractNode node : parameters)
                {
                    MyNode myNode = (MyNode) node;
                    returnValue += myNode.getValue();
                }
                return returnValue;
            case "MIN":
                returnValue = ((MyNode) parameters[0]).getValue();
                for(AbstractNode node : parameters)
                {
                    MyNode myNode = (MyNode) node;
                    if(myNode.getValue() < returnValue) {
                        returnValue = myNode.getValue();
                    }
                }
                return returnValue;
            case "MAX":
                returnValue = ((MyNode) parameters[0]).getValue();
                for(AbstractNode node : parameters)
                {
                    MyNode myNode = (MyNode) node;
                    if(myNode.getValue() > returnValue) {
                        returnValue = myNode.getValue();
                    }
                }
                return returnValue;
            case "PROMEDIO":
                double count = 0;
                for(AbstractNode node : parameters)
                {
                    MyNode myNode = (MyNode) node;
                    returnValue += myNode.getValue();
                    count +=1;
                }
                return returnValue/count;
            default:
        }
        return 0;
    }
}
