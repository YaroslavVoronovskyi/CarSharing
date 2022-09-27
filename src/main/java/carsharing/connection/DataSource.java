package carsharing.connection;

import carsharing.Constants;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static final BasicDataSource DATA_SOURCE = new BasicDataSource();
    private static final PropertiesDataLoader PROPERTIES_DATA_LOADER = new PropertiesDataLoader();

    static {
        try {
            DATA_SOURCE.setDriverClassName(PROPERTIES_DATA_LOADER.getProperty(Constants.DB_DRIVER));
            DATA_SOURCE.setUrl(PROPERTIES_DATA_LOADER.getProperty(Constants.DB_URL));
            DATA_SOURCE.setMinIdle(5);
            DATA_SOURCE.setMaxIdle(10);
            DATA_SOURCE.setMaxOpenPreparedStatements(100);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
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
