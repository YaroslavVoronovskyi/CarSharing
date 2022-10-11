package carsharing.service;

import carsharing.Constants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidatorTest {

    @Test
    public void shouldValidateValueFromConsole() {
        assertTrue(Validator.validateFieldValue("Yaroslav", Constants.NAME_CHECK_PATTERN));
        assertFalse(Validator.validateFieldValue("Yaroslav123", Constants.NAME_CHECK_PATTERN));
        assertFalse(Validator.validateFieldValue("Yaroslav 123", Constants.NAME_CHECK_PATTERN));
    }
}
