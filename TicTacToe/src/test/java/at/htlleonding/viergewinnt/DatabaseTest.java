package at.htlleonding.viergewinnt;

import at.htlleonding.viergewinnt.controller.Database;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @Test
    public void testGetDataSource() {
        DataSource dataSource = Database.getDataSource();
        assertNotNull(dataSource, "DataSource should not be null");

        try (Connection connection = dataSource.getConnection()) {
            assertFalse(connection.isClosed(), "Connection should be open");
        } catch (SQLException e) {
            fail("Should have established a connection: " + e.getMessage());
        }
    }
}