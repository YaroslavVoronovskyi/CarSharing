package carsharing.dao.impl;

import carsharing.Constants;
import carsharing.connection.DataSource;
import carsharing.dao.AbstractDao;
import carsharing.dao.ICarDao;
import carsharing.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDao extends AbstractDao<Car> implements ICarDao {

    @Override
    public List<Car> getAllCarsByCompanyId(int companyId) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.Queries.CAR_GET_ALL_QUERY_BY_COMPANY_ID)) {
            statement.setInt(Constants.FIRST_COLUMN_INDEX, companyId);
            return buildCarsList(statement);
        } catch (SQLException exception) {
            throw new RuntimeException("Could not get cars list with company id: " + companyId);
        }
    }

    @Override
    protected void createNewModel(PreparedStatement statement, Car car) throws SQLException {
        statement.setString(Constants.FIRST_COLUMN_INDEX, car.getName());
        statement.setInt(Constants.SECOND_COLUMN_INDEX, car.getCompanyId());
        statement.setBoolean(Constants.THIRD_COLUMN_INDEX, car.isRented());
        statement.execute();
    }

    @Override
    protected void updateModel(PreparedStatement statement, Car car) throws SQLException {
        statement.setObject(Constants.FIRST_COLUMN_INDEX, car.isRented());
        statement.setInt(Constants.SECOND_COLUMN_INDEX, car.getId());
        statement.executeUpdate();
    }

    @Override
    protected Car buildCustomModel(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt(Constants.FIRST_COLUMN_INDEX));
        car.setName(resultSet.getString(Constants.SECOND_COLUMN_INDEX));
        car.setCompanyId(resultSet.getInt(Constants.THIRD_COLUMN_INDEX));
        car.setRented(resultSet.getBoolean(Constants.FOURTH_COLUMN_INDEX));
        return car;
    }

    @Override
    protected String getByIdQuery() {
        return Constants.Queries.CAR_GET_QUERY;
    }

    @Override
    protected String getSaveQuery() {
        return Constants.Queries.CAR_SAVE_QUERY;
    }

    @Override
    protected String getUpdateQuery() {
        return Constants.Queries.CAR_UPDATE_QUERY;
    }

    private List<Car> buildCarsList(PreparedStatement statement) throws SQLException {
        List<Car> carsList = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Car car = new Car();
            car.setId(resultSet.getInt(Constants.FIRST_COLUMN_INDEX));
            car.setName(resultSet.getString(Constants.SECOND_COLUMN_INDEX));
            car.setCompanyId(resultSet.getInt(Constants.THIRD_COLUMN_INDEX));
            car.setRented(resultSet.getBoolean(Constants.FOURTH_COLUMN_INDEX));
            carsList.add(car);
        }
        return carsList;
    }
}
