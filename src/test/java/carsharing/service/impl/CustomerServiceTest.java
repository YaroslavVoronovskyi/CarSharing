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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomerServiceTest {

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
    public void methodShouldReturnExpectedCustomer() {
        Customer expectedCustomer = customerService.getById(1);
        Customer actualCustomer = createFakeCustomer();
        assertNotNull(expectedCustomer);
        assertEquals(expectedCustomer.getName(), actualCustomer.getName());
    }

    @Test
    public void methodShouldReturnExpectedCustomersList() {
        List<Customer> expectedCustomersList = customerService.getAll();
        List<Customer> actualCustomersList = createFakeCustomersList();
        assertNotNull(expectedCustomersList);
        assertEquals(expectedCustomersList.size(), 3);
        assertEquals(expectedCustomersList.get(0).getId(), actualCustomersList.get(0).getId());
        assertEquals(expectedCustomersList.get(0).getName(), actualCustomersList.get(0).getName());
    }

    @Test
    public void methodShouldSaveCustomer() {
        Customer customer = new Customer("Bill");
        customerService.save(customer);
        Mockito.verify(customerDaoMock, Mockito.times(1)).save(customer);
    }

    @Test
    public void methodShouldUpdateCustomer() {
        Customer customer = customerService.getById(1);
        customer.setRentedCarId(1);
        customerService.update(customer);
        Mockito.verify(customerDaoMock, Mockito.times(1)).update(customer);
    }

    private void initMocksForCustomer() {
        Mockito.when(customerDaoMock.getById(1)).thenReturn(createFakeCustomer());
    }

    private void initMocksForCustomers() {
        Mockito.when(customerDaoMock.getAll()).thenReturn(createFakeCustomersList());

    }

    private Customer createFakeCustomer() {
        Customer customer = new Customer("Yaroslav");
        customer.setId(1);
        return customer;
    }

    private List<Customer> createFakeCustomersList() {
        List<Customer> customersList = new ArrayList<>();

        Customer firstCustomer = new Customer("Yaroslav");
        Customer secondCustomer = new Customer("Piter");
        Customer thirdCustomer = new Customer("Boris");

        customersList.add(firstCustomer);
        customersList.add(secondCustomer);
        customersList.add(thirdCustomer);

        return customersList;
    }
}
