package controller;

import model.*;
import java.util.Scanner;

public class MenuController {
    private Conta conta;
    private Investimento investimento;
    private RelatorioInvestimento relatorioInvestimento;
    private RelatorioGanho relatorioGanho;
    private RelatorioPerda relatorioPerda;
    private Scanner scanner;

    public MenuController(Conta conta, Investimento investimento, RelatorioInvestimento relatorioInvestimento,
                          RelatorioGanho relatorioGanho, RelatorioPerda relatorioPerda) {
        this.conta = conta;
        this.investimento = investimento;
        this.relatorioInvestimento = relatorioInvestimento;
        this.relatorioGanho = relatorioGanho;
        this.relatorioPerda = relatorioPerda;
        this.scanner = new Scanner(System.in);
    }

    public void handleOption(int opcao) {
        switch (opcao) {
            case 1:
                System.out.println("Saldo: R$" + conta.verSaldo());
                break;
            case 2:
                System.out.print("Digite o valor para adicionar ao saldo: ");
                String valorAdicionarStr = scanner.nextLine();
                try {
                    double valorAdicionar = Double.parseDouble(valorAdicionarStr.replace(",", "."));
                    conta.adicionarSaldo(valorAdicionar);
                    System.out.println("Valor adicionado com sucesso. Novo saldo: R$" + conta.verSaldo());
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido. Por favor, insira um número válido.");
                }
                break;
            case 3:
                System.out.print("Digite o valor para retirar do saldo: ");
                double valorRetirar = scanner.nextDouble();
                conta.retirarSaldo(valorRetirar);
                System.out.println("Valor retirado com sucesso. Novo saldo: R$" + conta.verSaldo());
                break;
            case 4:


                break;
            case 5:
                relatorioInvestimento.mostrarInvestimento();
                break;
            case 6:
                relatorioGanho.mostrarGanhos();
                break;
            case 7:
                relatorioPerda.mostrarPerdas();
                break;
            case 8:
                System.out.println("Logout realizado com sucesso.");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
}
