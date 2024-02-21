package carsharing.dao.impl;

import carsharing.Constants;
import carsharing.dao.AbstractDao;
import carsharing.model.Company;
import carsharing.dao.ICompanyDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyDao extends AbstractDao<Company> implements ICompanyDao {

    @Override
    protected Company buildCustomModel(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getInt(Constants.FIRST_COLUMN_INDEX));
        company.setName(resultSet.getString(Constants.SECOND_COLUMN_INDEX));
        return company;
    }

    @Override
    protected void fillCreateStatement(PreparedStatement statement, Company model) throws SQLException {
        statement.setString(Constants.FIRST_COLUMN_INDEX, model.getName());
    }

    @Override
    protected String getByIdQuery() {
        return Constants.Queries.COMPANY_GET_QUERY;
    }

    @Override
    protected String getAllQuery() {
        return Constants.Queries.COMPANY_GET_ALL_QUERY;
    }

    @Override
    protected String getSaveQuery() {
        return Constants.Queries.COMPANY_SAVE_QUERY;
    }
}
