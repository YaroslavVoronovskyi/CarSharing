package carsharing.dao.impl;

import carsharing.dao.ICustomerDao;
import carsharing.model.Customer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerDaoTest extends AbstractDaoTest {

    private final ICustomerDao customerDao = new CustomerDao();

    @Test()
    public void shouldCreateNewCustomersAndReturnExpectedNumberOfCustomers() {
        List<Customer> customersList = customerDao.getAll();
        assertNotNull(customerDao);
        assertEquals(customersList.size(), 4);
    }

    @Test()
    public void shouldGetByIdAndReturnExpectedCustomer() {
        Customer firstCustomer = customerDao.getById(1);
        Customer secondCustomer = customerDao.getById(2);
        Customer thirdCustomer = customerDao.getById(3);
        assertNotNull(firstCustomer);
        assertEquals(firstCustomer.getId(), 1);
        assertEquals(firstCustomer.getName(), "Yaroslav");
        assertNotNull(secondCustomer);
        assertEquals(secondCustomer.getId(), 2);
        assertEquals(secondCustomer.getName(), "Boris");
        assertNotNull(thirdCustomer);
        assertEquals(thirdCustomer.getId(), 3);
        assertEquals(thirdCustomer.getName(), "Piter");
    }

    @Test()
    public void shouldUpdateCustomer() {
        Customer firstCustomer = customerDao.getById(1);
        firstCustomer.setRentedCarId(1);
        customerDao.update(firstCustomer);
        assertEquals(customerDao.getById(1).getRentedCarId(), 1);
    }
}
