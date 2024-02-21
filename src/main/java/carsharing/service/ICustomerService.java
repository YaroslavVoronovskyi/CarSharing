package carsharing.service;

import carsharing.model.Customer;

import java.util.List;

public interface ICustomerService {
    Customer getById(int id);

    List<Customer> getAll();

    void save(Customer customer);

    void update(Customer customer);
}
