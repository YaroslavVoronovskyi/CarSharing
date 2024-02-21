package carsharing.processors.impl.customer;

import carsharing.model.Customer;
import carsharing.service.ICustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.isA;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ReturnRentedCarProcessorTest {

    private final static String TEST_SUPPORTED_ACTION_TITLE = "2";
    private final static String TEST_CUSTOMER_NAME = "Yaroslav";
    private final static int TEST_CUSTOMER_ID = 1;
    private final static Integer TEST_CUSTOMER_RENTED_CAR_ID = 1;

    @Mock
    private ICustomerService customerServiceMock;
    @InjectMocks
    private ReturnRentedCarProcessor returnRentedCarProcessor;

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(returnRentedCarProcessor.getSupportedCustomerActionTitle(), TEST_SUPPORTED_ACTION_TITLE);
    }

    @Test
    public void shouldCheckReturnRentedCarProcessor() {
        Customer customer = createTestCustomer();
        Mockito.doNothing().when(customerServiceMock).update(isA(Customer.class));

        returnRentedCarProcessor.doActionWithCustomer(customer);

        Mockito.verify(customerServiceMock).update(createTestCustomer());
        assertNull(customer.getRentedCarId());
    }

    @Test
    public void shouldShowMassageThatCustomerDidNotRentCar() {
        Mockito.doNothing().when(customerServiceMock).update(isA(Customer.class));
        returnRentedCarProcessor.doActionWithCustomer(createTestCustomerWithoutCar());
        Mockito.verify(customerServiceMock, Mockito.times(0)).getById(TEST_CUSTOMER_ID);
    }

    private Customer createTestCustomer() {
        Customer customer = new Customer(TEST_CUSTOMER_NAME);
        customer.setId(TEST_CUSTOMER_ID);
        customer.setRentedCarId(TEST_CUSTOMER_RENTED_CAR_ID);
        return customer;
    }

    private Customer createTestCustomerWithoutCar() {
        Customer customer = new Customer(TEST_CUSTOMER_NAME);
        customer.setId(TEST_CUSTOMER_ID);
        customer.setRentedCarId(null);
        return customer;
    }
}
