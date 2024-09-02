package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.RelatorioGanho;

import database.DatabaseConnection;

public class RelatorioGanhosDAO {

    public static List<RelatorioGanho> getAllGanhos() {
        List<RelatorioGanho> ganhos = new ArrayList<>();

        String query = "SELECT * FROM relatorios_ganhos";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
            RelatorioGanho ganho = new RelatorioGanho();
                ganho.setId(resultSet.getInt("id"));
                ganho.setContaId(resultSet.getInt("conta_id"));
                ganho.setData(resultSet.getDate("data"));
                ganho.setValor(resultSet.getString("valor"));
                ganho.setOrigem(resultSet.getString("origem"));

                ganhos.add(ganho);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ganhos;
    }
}
