package carsharing.service;

import carsharing.Constants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidatorTest {

    private final static String TEST_VALID_VALUE = "Yaroslav";
    private final static String TEST_NOT_VALID_VALUE = "Yaroslav123";
    @Test
    public void shouldValidateValueFromConsole() {
        assertTrue(Validator.validateFieldValue(TEST_VALID_VALUE, Constants.NAME_CHECK_PATTERN));
        assertFalse(Validator.validateFieldValue(TEST_NOT_VALID_VALUE, Constants.NAME_CHECK_PATTERN));
    }
}
