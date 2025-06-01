package at.htlleonding.viergewinnt.controller;

import at.htlleonding.viergewinnt.model.CurrentPlayer;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    DataSource dataSource = Database.getDataSource();


    public void insert(CurrentPlayer winner) {

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

    public void deleteWinner(int id) {
        String sql = "DELETE FROM GAMES WHERE ID=?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<String> getWinnerList() {
        String sql = "SELECT * FROM GAMES";
        List<String> gameList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String winner = resultSet.getString("WINNER");
                gameList.add(winner);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gameList;
    }
}
