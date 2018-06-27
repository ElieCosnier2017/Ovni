package fr.eni.clinique.ihm;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ConnexionFrame extends JFrame {

    private static final long serialVersionUID = 2599708452804266382L;

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private  JTextField usernameField;
    private JPasswordField passwordField;
    private JPanel containerLogin;
    private JPanel containerBtn;
    private JButton valider;

    public ConnexionFrame(){
        this.setTitle("Connexion");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.initComponents();
        this.initListeners();
    }

    private void initComponents() {
        containerLogin = new JPanel();
        containerLogin.setLayout(new GridBagLayout());


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        valider = new JButton("OK");
        containerBtn = new JPanel();

        containerBtn.add(valider);

        gbc.gridy = 0;
        gbc.gridx = 0;
        containerLogin.add( new JLabel("Utilisateur:"), gbc);

        gbc.gridx = 1;
        containerLogin.add( getUsernameField(), gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        containerLogin.add( new JLabel("Mot de passe:"), gbc);

        gbc.gridx = 1;
        containerLogin.add( getPasswordField(), gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;

        containerLogin.add(containerBtn, gbc);

        this.setContentPane(containerLogin);
    }

    public JTextField getUsernameField() {
        if (usernameField == null) {
            usernameField = new JTextField(10);
        }
        return usernameField;
    }

    public JTextField getPasswordField() {
        if (passwordField == null) {
            passwordField = new JPasswordField(10);
        }
        return passwordField;
    }

    private void initListeners() {
        valider.addActionListener((e)-> {
//            ConnexionController.get().logUser();
        });
    }

    // Lancement de l'application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                ConnexionFrame ecran = new ConnexionFrame();
				ecran.setVisible(true);


            }
        });

    }
}
