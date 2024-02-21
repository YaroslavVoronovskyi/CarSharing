package carsharing.connection;

import carsharing.Constants;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static final BasicDataSource DATA_SOURCE = new BasicDataSource();

    static {
        DATA_SOURCE.setDriverClassName(PropertiesDataLoader.getPropertyValue(Constants.DB_DRIVER));
        DATA_SOURCE.setUrl(PropertiesDataLoader.getPropertyValue(Constants.DB_URL));
        DATA_SOURCE.setMinIdle(5);
        DATA_SOURCE.setMaxIdle(10);
        DATA_SOURCE.setMaxOpenPreparedStatements(100);
    }

    private DataSource() {
    }

    public static Connection getConnection() {
        try {
            return DATA_SOURCE.getConnection();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
