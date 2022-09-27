package carsharing.dao.impl;

import carsharing.Constants;
import carsharing.connection.DataSource;
import carsharing.model.Customer;
import carsharing.dao.ICustomerDao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements ICustomerDao {
    @Override
    public Customer getById(int id) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.Queries.CUSTOMER_GET_QUERY)) {
            statement.setInt(Constants.FIRST_COLUMN_INDEX, id);
            List<Customer> customersList = buildCustomersList(statement);
            if (customersList.size() == 0) {
                return null;
            }
            return customersList.get(0);
        } catch (SQLException exception) {
            throw new RuntimeException("Could not get customer with id: " + id);
        }
    }

    @Override
    public List<Customer> getAll() {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.Queries.CUSTOMER_GET_ALL_QUERY)) {
            return buildCustomersList(statement);
        } catch (SQLException exception) {
            throw new RuntimeException("Could not get customers list");
        }
    }

    @Override
    public void save(Customer customer) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.Queries.CUSTOMER_INSERT_QUERY)) {
            statement.setString(Constants.FIRST_COLUMN_INDEX, customer.getName());
            statement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException("Could not save customer");
        }
    }

    @Override
    public void update(Customer customer) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.Queries.CUSTOMER_UPDATE_QUERY)) {
            statement.setObject(Constants.FIRST_COLUMN_INDEX, customer.getRentedCarId());
            statement.setInt(Constants.SECOND_COLUMN_INDEX, customer.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Could not update customer with id: " + customer.getId());
        }
    }

    @Override
    public void updateCustomerLeftRentedCar(Customer customer) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.Queries.CUSTOMER_UPDATE_QUERY)) {
            statement.setObject(Constants.FIRST_COLUMN_INDEX, customer.getRentedCarId());
            statement.setInt(Constants.SECOND_COLUMN_INDEX, customer.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Could not update customer with id: " + customer.getId());
        }
    }

    private List<Customer> buildCustomersList(PreparedStatement statement) throws SQLException {
        List<Customer> customersList = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setId(resultSet.getInt(Constants.FIRST_COLUMN_INDEX));
            customer.setName(resultSet.getString(Constants.SECOND_COLUMN_INDEX));
            customer.setRentedCarId(resultSet.getInt(Constants.THIRD_COLUMN_INDEX));
            customersList.add(customer);
        }
        return customersList;
    }
}
