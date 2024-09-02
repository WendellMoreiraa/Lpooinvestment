package dao;

import model.User;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User getUserByEmailAndPassword(String email, String senha) {
        User user = null;
        String query = "SELECT * FROM contas WHERE email = ? AND senha = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, senha);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(resultSet.getString("email"), resultSet.getString("senha"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
