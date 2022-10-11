package carsharing;

import carsharing.connection.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Initializer {
    public static void innitDataBase() {
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(Constants.Queries.CREATE_TABLE_COMPANY_SQL_QUERY);
            statement.execute(Constants.Queries.CREATE_TABLE_CAR_SQL_QUERY);
            statement.execute(Constants.Queries.CREATE_TABLE_CUSTOMER_SQL_QUERY);
        } catch (SQLException exception) {
            throw new RuntimeException("Could not created the tables...");
        }
    }
}
