package carsharing.dao.impl;

import carsharing.Constants;
import carsharing.model.Car;
import carsharing.connection.DataSource;
import carsharing.dao.ICarDao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDao implements ICarDao {

    @Override
    public Car getById(int id) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.Queries.CAR_GET_QUERY)) {
            statement.setInt(Constants.FIRST_COLUMN_INDEX, id);
            List<Car> carsList = buildCarsList(statement);
            if (carsList.size() == 0) {
                return null;
            }
            return carsList.get(0);
        } catch (SQLException exception) {
            throw new RuntimeException("Could not get car with id: " + id);
        }
    }

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
    public void save(Car car) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.Queries.CAR_INSERT_QUERY)) {
            statement.setString(Constants.FIRST_COLUMN_INDEX, car.getName());
            statement.setInt(Constants.SECOND_COLUMN_INDEX, car.getCompanyId());
            statement.setBoolean(Constants.THIRD_COLUMN_INDEX, car.isRented());
            statement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException("Could not save car");
        }
    }

    @Override
    public void update(Car car) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.Queries.CAR_UPDATE_QUERY)) {
            statement.setObject(Constants.FIRST_COLUMN_INDEX, car.isRented());
            statement.setInt(Constants.SECOND_COLUMN_INDEX, car.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Could not update car with id: " + car.getId());
        }
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
