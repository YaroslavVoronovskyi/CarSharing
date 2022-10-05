package carsharing.dao;

import carsharing.Constants;
import carsharing.connection.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> implements IAbstractDao<T> {

    public T getById(int id) {
        String query = getByIdQuery();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(Constants.FIRST_COLUMN_INDEX, id);
            List<T> modelsList = buildModelFromResultSet(statement);
            if (modelsList.size() == 0) {
                return null;
            }
            return modelsList.get(0);
        } catch (SQLException exception) {
            throw new RuntimeException("Could not get data from DB with id: " + id);
        }
    }

    public List<T> getAll() {
        String query = getAllQuery();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            return buildModelFromResultSet(statement);
        } catch (SQLException exception) {
            throw new RuntimeException("Could not get data from DB by query: " + query);
        }
    }

    public void save(T model) {
        String query = getSaveQuery();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            fillCreateStatement(statement, model);
            statement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException("Could not save data to DB by query: " + query);
        }
    }

    public void update(T model) {
        String query = getUpdateQuery();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            fillUpdateStatement(statement, model);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Could not update data to DB by query: " + query);
        }
    }

    private List<T> buildModelFromResultSet(PreparedStatement statement) throws SQLException {
        List<T> modelsList = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            modelsList.add(buildCustomModel(resultSet));
        }
        return modelsList;
    }

    protected void fillUpdateStatement(PreparedStatement statement, T model) throws SQLException {
        throw new UnsupportedOperationException("This method is not implemented");
    }

    protected void fillCreateStatement(PreparedStatement statement, T model) throws SQLException {
        throw new UnsupportedOperationException("This method is not implemented");
    }

    protected T buildCustomModel(ResultSet resultSet) throws SQLException {
        throw new UnsupportedOperationException("This method is not implemented");
    }

    protected String getByIdQuery() {
        throw new UnsupportedOperationException("This method is not implemented");
    }

    protected String getAllQuery() {
        throw new UnsupportedOperationException("This method is not implemented");
    }

    protected String getSaveQuery() {
        throw new UnsupportedOperationException("This method is not implemented");
    }

    protected String getUpdateQuery() {
        throw new UnsupportedOperationException("This method is not implemented");
    }
}
