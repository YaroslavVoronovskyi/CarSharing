package carsharing;

import carsharing.connection.DataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class InitializerTest {

    @Mock
    private Connection connectionMock;
    @Mock
    private Statement statementMock;

    @Test
    public void shouldInitDataBase() throws SQLException {
        try (MockedStatic<DataSource> mockStatic = Mockito.mockStatic(DataSource.class)) {
            mockStatic.when(DataSource::getConnection).thenReturn(connectionMock);
            Mockito.when(connectionMock.createStatement()).thenReturn(statementMock);
            Mockito.when(statementMock.execute(Constants.Queries.CREATE_TABLE_COMPANY_SQL_QUERY)).thenReturn(true);
            Mockito.when(statementMock.execute(Constants.Queries.CREATE_TABLE_CUSTOMER_SQL_QUERY)).thenReturn(true);
            Mockito.when(statementMock.execute(Constants.Queries.CREATE_TABLE_CAR_SQL_QUERY)).thenReturn(true);

            Initializer.innitDataBase();

            Mockito.verify(statementMock).execute(Constants.Queries.CREATE_TABLE_COMPANY_SQL_QUERY);
            Mockito.verify(statementMock).execute(Constants.Queries.CREATE_TABLE_CUSTOMER_SQL_QUERY);
            Mockito.verify(statementMock).execute(Constants.Queries.CREATE_TABLE_CAR_SQL_QUERY);
        }
    }
}
