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
        customer.setRentedCarId(resultSet.getObject(Constants.THIRD_COLUMN_INDEX, Integer.class));
        return customer;
    }

    @Override
    protected void fillCreateStatement(PreparedStatement statement, Customer model) throws SQLException {
        statement.setString(Constants.FIRST_COLUMN_INDEX, model.getName());
    }

    @Override
    protected void fillUpdateStatement(PreparedStatement statement, Customer model) throws SQLException {
        statement.setInt(Constants.FIRST_COLUMN_INDEX, model.getRentedCarId());
        statement.setInt(Constants.SECOND_COLUMN_INDEX, model.getId());
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
