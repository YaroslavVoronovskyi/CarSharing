package carsharing.dao.impl;

import carsharing.Constants;
import carsharing.dao.AbstractDao;
import carsharing.model.Customer;
import carsharing.dao.ICustomerDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao extends AbstractDao<Customer> implements ICustomerDao {

    @Override
    protected Customer buildCustomModel(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getInt(Constants.FIRST_COLUMN_INDEX));
        customer.setName(resultSet.getString(Constants.SECOND_COLUMN_INDEX));
        customer.setRentedCarId(resultSet.getInt(Constants.THIRD_COLUMN_INDEX));
        return customer;
    }

    @Override
    protected void createNewModel(PreparedStatement statement, Customer customer) throws SQLException {
        statement.setString(Constants.FIRST_COLUMN_INDEX, customer.getName());
        statement.executeUpdate();
    }

    @Override
    protected void updateModel(PreparedStatement statement, Customer customer) throws SQLException {
        statement.setObject(Constants.FIRST_COLUMN_INDEX, customer.getRentedCarId());
        statement.setInt(Constants.SECOND_COLUMN_INDEX, customer.getId());
        statement.executeUpdate();
    }

    @Override
    protected String getByIdQuery() {
        return Constants.Queries.CUSTOMER_GET_QUERY;
    }

    @Override
    protected String getAllQuery() {
        return Constants.Queries.CUSTOMER_GET_ALL_QUERY;
    }

    @Override
    protected String getSaveQuery() {
        return Constants.Queries.CUSTOMER_SAVE_QUERY;
    }

    @Override
    protected String getUpdateQuery() {
        return Constants.Queries.CUSTOMER_UPDATE_QUERY;
    }
}
