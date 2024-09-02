 package dao;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaDAO {

    public double getSaldo(int contaId) {
        double saldo = 0.0;
        String query = "SELECT saldo FROM contas WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, contaId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                saldo = resultSet.getDouble("saldo");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return saldo;
    }

    public void adicionarSaldo(int contaId, double valor) {
        String query = "UPDATE contas SET saldo = saldo + ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, valor);
            preparedStatement.setInt(2, contaId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void retirarSaldo(int contaId, double valor) {
        String query = "UPDATE contas SET saldo = saldo - ? WHERE id = ? AND saldo >= ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, valor);
            preparedStatement.setInt(2, contaId);
            preparedStatement.setDouble(3, valor);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated == 0) {
                System.out.println("Saldo insuficiente.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getContaIdByEmail(String email) {
        int contaId = -1;
        String query = "SELECT id FROM contas WHERE email = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                contaId = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contaId;
    }

}