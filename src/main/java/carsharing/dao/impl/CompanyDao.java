package carsharing.dao.impl;

import carsharing.Constants;
import carsharing.connection.DataSource;
import carsharing.model.Company;
import carsharing.dao.ICompanyDao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao implements ICompanyDao {

    @Override
    public Company getById(int id) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.Queries.COMPANY_GET_QUERY)) {
            statement.setInt(Constants.FIRST_COLUMN_INDEX, id);
            List<Company> companiesList = buildCompaniesList(statement);
            if (companiesList.size() == 0) {
                return null;
            }
            return companiesList.get(0);
        } catch (SQLException exception) {
            throw new RuntimeException("Could not get company with id: " + id);
        }
    }

    @Override
    public List<Company> getAll() {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.Queries.COMPANY_GET_ALL_QUERY)) {
            return buildCompaniesList(statement);
        } catch (SQLException exception) {
            throw new RuntimeException("Could not get companies list");
        }
    }

    @Override
    public void save(Company company) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.Queries.COMPANY_INSERT_QUERY)) {
            statement.setString(Constants.FIRST_COLUMN_INDEX, company.getName());
            statement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException("Could not save company");
        }
    }

    private List<Company> buildCompaniesList(PreparedStatement statement) throws SQLException {
        List<Company> companiesList = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Company company = new Company();
            company.setId(resultSet.getInt(Constants.FIRST_COLUMN_INDEX));
            company.setName(resultSet.getString(Constants.SECOND_COLUMN_INDEX));
            companiesList.add(company);
        }
        return companiesList;
    }
}
