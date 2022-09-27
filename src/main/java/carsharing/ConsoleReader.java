package carsharing;

import carsharing.service.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class ConsoleReader {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    public static String getStringFromConsole(String message) {
        System.out.println(message);
        return getValueFromConsole();
    }

    public static String getStringFromConsole(String message, Pattern pattern, String errorMessage) {
        System.out.println(message);
        String value = null;
        boolean isValueValid = false;
        while (!isValueValid) {
            value = getValueFromConsole();
            isValueValid = Validator.validateFieldValue(value, pattern);
            if (!isValueValid) {
                System.out.println(errorMessage + Constants.LINE_SEPARATOR + message);
            }
        }
        return value;
    }

    public static int getIntFromConsole(int maxIndex) {
        int recordNumber = 0;
        boolean resultNotValid = false;
        do {
            if (resultNotValid) {
                System.out.println("Please enter correct number");
            }
            try {
                recordNumber = Integer.parseInt(READER.readLine());
                resultNotValid = recordNumber <= 0 || recordNumber > maxIndex;
            } catch (NumberFormatException exception) {
                resultNotValid = true;
            } catch (IOException exception) {
                throw new RuntimeException(Constants.READ_FROM_CONSOLE_ERROR_MESSAGE);
            }
        } while (resultNotValid);
        return recordNumber;
    }

    private static String getValueFromConsole() {
        try {
            return READER.readLine();
        } catch (IOException exception) {
            throw new RuntimeException(Constants.READ_FROM_CONSOLE_ERROR_MESSAGE);
        }
    }
}
