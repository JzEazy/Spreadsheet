package ParserHandling;

/**
 * Validates if a given string is a valid Excel-like coordinate.
 * A valid coordinate starts with one or more letters followed by one or more digits.
 */

public class TextInputCheck {
    public boolean isValidCoordinate(String input) {
        if (input == null) {
            return false;
        }
        String regex = "^[A-Z]+\\d+$"; // Regex for matching Excel-like coordinates
        return input.matches(regex);
    }
}
