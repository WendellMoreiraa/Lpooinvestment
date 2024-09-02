package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.RelatorioPerda;

import database.DatabaseConnection;

public class RelatorioPerdasDAO {

    public static List<RelatorioPerda> getAllPerdas() {
        List<RelatorioPerda> perdas = new ArrayList<>();

        String query = "SELECT * FROM relatorios_perdas";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                RelatorioPerda perda = new RelatorioPerda();
                perda.setId(resultSet.getInt("id"));
                perda.setContaId(resultSet.getInt("conta_id"));
                perda.setData(resultSet.getDate("data"));
                perda.setValor(resultSet.getString("valor"));
                perda.setOrigem(resultSet.getString("origem"));

                perdas.add(perda);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return perdas;
    }
}
