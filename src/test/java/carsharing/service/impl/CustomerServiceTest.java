package carsharing.service.impl;

import carsharing.dao.ICustomerDao;
import carsharing.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomerServiceTest {

    private final static String TEST_CUSTOMER_NAME = "Yaroslav";
    private final static int TEST_CUSTOMER_ID = 1;
    private final static int TEST_CAR_ID = 1;
    @Mock
    ICustomerDao customerDaoMock;
    @InjectMocks
    CustomerService customerService;

    @BeforeEach
    public void init() {
        initMocksForCustomers();
        initMocksForCustomer();
    }

    @Test
    public void shouldReturnExpectedCustomer() {
        Customer expectedCustomer = customerService.getById(TEST_CUSTOMER_ID);
        Customer actualCustomer = createTestCustomer();
        assertEquals(expectedCustomer, actualCustomer);
    }

    @Test
    public void shouldReturnExpectedCustomersList() {
        List<Customer> expectedCustomersList = customerService.getAll();
        List<Customer> actualCustomersList = List.of(createTestCustomer());
        assertEquals(expectedCustomersList, actualCustomersList);
    }

    @Test
    public void shouldSaveCustomer() {
        Customer customer = new Customer(TEST_CUSTOMER_NAME);
        customerService.save(customer);
        Mockito.verify(customerDaoMock).save(customer);
    }

    @Test
    public void shouldUpdateCustomer() {
        Customer customer = customerService.getById(TEST_CUSTOMER_ID);
        customer.setRentedCarId(TEST_CAR_ID);
        customerService.update(customer);
        Mockito.verify(customerDaoMock).update(customer);
    }

    private void initMocksForCustomer() {
        Mockito.when(customerDaoMock.getById(TEST_CUSTOMER_ID)).thenReturn(createTestCustomer());
    }

    private void initMocksForCustomers() {
        Mockito.when(customerDaoMock.getAll()).thenReturn(List.of(createTestCustomer()));

    }

    private Customer createTestCustomer() {
        Customer customer = new Customer(TEST_CUSTOMER_NAME);
        customer.setId(TEST_CUSTOMER_ID);
        return customer;
    }
}
