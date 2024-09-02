package dao;

import database.DatabaseConnection;
import model.Investimento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.Random;

public class InvestimentoDAO {



    public void registrarInvestimento(int contaId, double valor, String tipo) {
        Random rand = new Random();
        int id = rand.nextInt(20);
        String query = "INSERT INTO investimentos (id,conta_id, valor, data, tipo) VALUES (?, ?, ?, ?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, contaId);
            preparedStatement.setDouble(3, valor);
            preparedStatement.setDate(4, new Date(System.currentTimeMillis()));
            preparedStatement.setString(5, tipo);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
