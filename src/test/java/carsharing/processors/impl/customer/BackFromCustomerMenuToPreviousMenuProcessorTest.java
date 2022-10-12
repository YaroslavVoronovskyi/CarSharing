package carsharing.processors.impl.customer;

import carsharing.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BackFromCustomerMenuToPreviousMenuProcessorTest {

    private final static String TEST_SUPPORTED_ACTION_TITLE = "0";
    private final static String TEST_CUSTOMER_NAME = "Yaroslav";
    @InjectMocks
    private BackFromCustomerMenuToPreviousMenuProcessor backFromCustomerMenuToPreviousMenuProcessor;

    @Test
    public void shouldBackToPreviousMenu() {
        assertFalse(backFromCustomerMenuToPreviousMenuProcessor.doActionWithCustomer(new Customer(TEST_CUSTOMER_NAME)));
    }

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(backFromCustomerMenuToPreviousMenuProcessor.getSupportedCustomerActionTitle(), TEST_SUPPORTED_ACTION_TITLE);
    }
}
