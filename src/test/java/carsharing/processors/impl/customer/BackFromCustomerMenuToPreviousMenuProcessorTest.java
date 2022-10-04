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

    @InjectMocks
    private BackFromCustomerMenuToPreviousMenuProcessor backFromCustomerMenuToPreviousMenuProcessor;

    @Test
    public void shouldBackToPreviousMenu() {
        assertFalse(backFromCustomerMenuToPreviousMenuProcessor.doActionWithCustomer(new Customer("Yaroslav")));
    }

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(backFromCustomerMenuToPreviousMenuProcessor.getSupportedCustomerActionTitle(), "0");
    }
}
