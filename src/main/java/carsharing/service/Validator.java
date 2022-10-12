package carsharing.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean validateFieldValue(String value, Pattern pattern) {
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
