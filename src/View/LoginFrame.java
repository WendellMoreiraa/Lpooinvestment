package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import dao.UserDAO;
import model.User;
import database.DatabaseConnection;

public class LoginFrame extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginFrame() {

        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));


        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Senha:");
        passwordField = new JPasswordField();


        JButton loginButton = new JButton("Login");


        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DatabaseConnection.getConnection();
                    UserDAO userDAO = new UserDAO();

                    String email = emailField.getText();
                    String senha = new String(passwordField.getPassword());

                    User loggedInUser = userDAO.getUserByEmailAndPassword(email, senha);

                    if (loggedInUser != null) {
                        JOptionPane.showMessageDialog(null, "Login realizado com sucesso! Bem-vindo, " + loggedInUser.getUsername());
                        // Fechar a tela de login e abrir a tela principal
                        dispose();  // Fecha a janela de login
                        MainFrame mainFrame = new MainFrame();
                        mainFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Email ou senha incorretos. Tente novamente.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        // Exibe a tela de login
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
}
