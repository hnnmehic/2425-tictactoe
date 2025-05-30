package at.htlleonding.viergewinnt.controller;

import javax.sql.DataSource;
import java.sql.*;

public class Repository {

    DataSource dataSource = Database.getDataSource();


    public void insert(char winner) {

        String sql = "INSERT INTO GAMES (winner) values (?)";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            System.out.println("Datenbankverbindung erfolgreich");

            preparedStatement.setString(1,String.valueOf(winner));
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                System.out.println("Game inserted with ID: "+ generatedId);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
