package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.LoginMger;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Personnel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ConnexionFrame extends JFrame {

    private static final long serialVersionUID = 2599708452804266382L;

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

	private JPanel contentPane;
	private JTextField user;
	private JPasswordField password;
    private JPanel containerBtn;
    private JTextField textField;
    private JButton valider;

    public ConnexionFrame(){
		this.setTitle("Connexion - Ani' Forme");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
        this.initComponents();
        this.initListeners();
    }

    private void initComponents() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/Logo.PNG")));
		lblLogo.setBounds(0, 0, 434, 136);
		contentPane.add(lblLogo);
		
		JLabel lblUtilisateur = new JLabel("Utilisateur :");
		lblUtilisateur.setFont(new Font("Calibri", Font.BOLD, 14));
		lblUtilisateur.setBounds(105, 147, 85, 31);
		contentPane.add(lblUtilisateur);
		
		user = new JTextField();
		user.setBounds(230, 152, 109, 20);
		contentPane.add(user);
		user.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setFont(new Font("Calibri", Font.BOLD, 14));
		lblMotDePasse.setBounds(91, 189, 109, 20);
		contentPane.add(lblMotDePasse);
		
		password = new JPasswordField();
		password.setBounds(230, 186, 109, 20);
		contentPane.add(password);
		
		valider = new JButton("Connexion");
		valider.setBounds(156, 227, 136, 23);
		contentPane.add(valider);
        
    }


    public JTextField getUsernameField() {
        if (user == null) {
            user = new JTextField(10);
        }
        return user;
    }

    public JTextField getPasswordField() {
        if (password == null) {
            password = new JPasswordField(10);
        }
        return password;
    }

    private void initListeners() {
        valider.addActionListener((e)-> {
            try {
                LoginMger mger = LoginMger.getInstance();
                PersonnelManager personnelManager = PersonnelManager.getInstance();
                System.out.println(getUsernameField().getText());
                System.out.println(getPasswordField().getText());

                Boolean cnxValid = mger.verifyUser(getUsernameField().getText(), getPasswordField().getText());
                if(!cnxValid){
                    JLabel lblconnexion = new JLabel("Erreur lors de l'authentification");
                    lblconnexion.setBounds(135, 195, 200, 40);
                    lblconnexion.setForeground(Color.MAGENTA);
                    contentPane.add(lblconnexion);
                    contentPane.updateUI();
                }else {
                    Personnel personnel = personnelManager.selectOneByNameAndMotPasse(getUsernameField().getText(), getPasswordField().getText());
                    this.dispose();
                    GeneralFrame cnx = new GeneralFrame(personnel);
                    cnx.setVisible(true);
                }
            } catch (BLLException e1) {
                e1.printStackTrace();
            }
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
