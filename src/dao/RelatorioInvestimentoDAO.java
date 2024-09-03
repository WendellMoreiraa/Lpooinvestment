package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.RelatorioInvestimento;
import database.DatabaseConnection;

public class RelatorioInvestimentoDAO {

    public static List<RelatorioInvestimento> getAllInvestimentos() {
        List<RelatorioInvestimento> investimentos = new ArrayList<>();

        String query = "SELECT * FROM investimentos";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                RelatorioInvestimento investimento = new RelatorioInvestimento();
                investimento.setId(resultSet.getInt("id"));
                investimento.setContaId(resultSet.getInt("conta_id"));
                investimento.setValor(resultSet.getInt("valor"));
                investimento.setData(resultSet.getDate("data"));
                investimento.setTipo(resultSet.getString("tipo"));
                investimentos.add(investimento);
            }
            System.out.println("Total de investimentos recuperados: " + investimentos.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return investimentos;

    }
}
