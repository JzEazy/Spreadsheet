package ParserHandling;

import SheetHandling.CellDataTypes.ContentType;

public class Classifier {
    public ContentType classify(String input) {
        if(input.isEmpty()) {
            return ContentType.EMPTY;
        }
        if (input.startsWith("=")) {
            // Check if the substring after '=' is a valid number
            String substring = input.substring(1);
            if (isNumeric(substring)) {
                return ContentType.NUMERICAL;
            } else {
                return ContentType.FORMULA;
            }
        } else {
            return ContentType.TEXT;
        }
    }

    private boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
