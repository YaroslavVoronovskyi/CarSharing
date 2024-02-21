package carsharing.service.impl;

import carsharing.dao.ICustomerDao;
import carsharing.model.Customer;
import carsharing.service.ICustomerService;

import java.util.List;

public class CustomerService implements ICustomerService {

    private final ICustomerDao customerDao;

    public CustomerService(ICustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Customer getById(int id) {
        return customerDao.getById(id);
    }

    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    @Override
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }
}
