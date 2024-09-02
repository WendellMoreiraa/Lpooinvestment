package View;
import model.*;

import controller.*;
import dao.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainFrame extends JFrame {

    private Conta conta;
    private Investimento investimento;

    public MainFrame() {

        setTitle("Sistema de Investimentos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        int contaId = 11;
        conta = new Conta(contaId);
        investimento = new Investimento(conta);
        RelatorioInvestimento relatorioInvestimento = new RelatorioInvestimento();
        RelatorioGanho relatorioGanho = new RelatorioGanho();
        RelatorioPerda relatorioPerda = new RelatorioPerda();
        MenuController menuController = new MenuController(conta, investimento, relatorioInvestimento, relatorioGanho, relatorioPerda);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));


        JButton btnVerSaldo = new JButton("Ver Saldo");
        JButton btnAdicionarSaldo = new JButton("Adicionar Saldo");
        JButton btnRetirarSaldo = new JButton("Retirar Saldo");
        JButton btnInvestir = new JButton("Investir");
        JButton btnRelatorioInvestimentos = new JButton("Mostrar Relatório de Investimentos");
        JButton btnRelatorioGanhos = new JButton("Mostrar Ganhos");
        JButton btnRelatorioPerdas = new JButton("Mostrar Perdas");
        JButton btnLogout = new JButton("Logout");

        panel.add(btnVerSaldo);
        panel.add(btnAdicionarSaldo);
        panel.add(btnRetirarSaldo);
        panel.add(btnInvestir);
        panel.add(btnRelatorioInvestimentos);
        panel.add(btnRelatorioGanhos);
        panel.add(btnRelatorioPerdas);
        panel.add(btnLogout);


        add(panel, BorderLayout.CENTER);


        btnVerSaldo.addActionListener(_ -> {
            double saldo = conta.verSaldo();
            JOptionPane.showMessageDialog(null, "Seu saldo atual é: R$" + saldo);
        });


        btnAdicionarSaldo.addActionListener(_ -> {
            String valorStr = JOptionPane.showInputDialog("Digite o valor a ser adicionado:");
            try {
                double valor = Double.parseDouble(valorStr);
                conta.adicionarSaldo(valor);
                JOptionPane.showMessageDialog(null, "R$" + valor + " adicionado ao seu saldo com sucesso.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Valor inválido.");
            }
        });


        btnRetirarSaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valorStr = JOptionPane.showInputDialog("Digite o valor a ser retirado:");
                try {
                    double valor = Double.parseDouble(valorStr);
                    if (conta.retirarSaldo(valor)) {
                        JOptionPane.showMessageDialog(null, "R$" + valor + " retirado do seu saldo com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Saldo insuficiente.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor inválido.");
                }
            }
        });
        btnInvestir.addActionListener(e -> {
            String[] tipos = {"Poupança", "CDB", "Ações", "Fundos Imobiliários"};
            String tipo = (String) JOptionPane.showInputDialog(null, "Escolha o tipo de investimento",
                    "Investir", JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);
            if (tipo != null) {
                String valorStr = JOptionPane.showInputDialog("Digite o valor do investimento:");
                try {
                    double valor = Double.parseDouble(valorStr);
                    investimento.investir(valor, tipo);
                    JOptionPane.showMessageDialog(null,"Investimento de R$" + valor + " no tipo " + tipo + " realizado com sucesso.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor inválido.");
                }
            }
        });


        btnRelatorioInvestimentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<RelatorioInvestimento> investimentos = RelatorioInvestimentoDAO.getAllInvestimentos();
                StringBuilder relatorio = new StringBuilder("Relatório de Investimentos:\n\n");
                for (RelatorioInvestimento inv : investimentos) {

                    relatorio.append("Descrição:").append(inv.getDescricao()).append("\n");
                    relatorio.append("Data: ").append(inv.getData()).append("\n\n");
                }
                JOptionPane.showMessageDialog(null, relatorio.toString());
            }
        });

        btnRelatorioGanhos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<RelatorioGanho> ganhos = RelatorioGanhosDAO.getAllGanhos();
                StringBuilder relatorio = new StringBuilder("Relatório de Ganhos:\n\n");
                for (RelatorioGanho ganho : ganhos) {
                    relatorio.append("Origem: ").append(ganho.getOrigem()).append("\n");
                    relatorio.append("Valor: R$").append(ganho.getValor()).append("\n");
                    relatorio.append("Data: ").append(ganho.getData()).append("\n\n");
                }
                JOptionPane.showMessageDialog(null, relatorio.toString());
            }
        });


        btnRelatorioPerdas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<RelatorioPerda> perdas = RelatorioPerdasDAO.getAllPerdas();
                StringBuilder relatorio = new StringBuilder("Relatório de Perdas:\n\n");
                for (RelatorioPerda perda : perdas) {
                    relatorio.append("Origem: ").append(perda.getOrigem()).append("\n");
                    relatorio.append("Valor: R$").append(perda.getValor()).append("\n");
                    relatorio.append("Data: ").append(perda.getData()).append("\n\n");
                }
                JOptionPane.showMessageDialog(null, relatorio.toString());
            }
        });


        btnLogout.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Logout realizado com sucesso.");
            System.exit(0);
        });
    }
}
