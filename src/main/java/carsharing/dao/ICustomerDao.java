package carsharing.dao;

import carsharing.model.Customer;

import java.util.List;

public interface ICustomerDao {

    Customer getById(int id);

    List<Customer> getAll();

    void save(Customer customer);

    void update(Customer customer);

    void updateCustomerLeftRentedCar(Customer customer);
}
