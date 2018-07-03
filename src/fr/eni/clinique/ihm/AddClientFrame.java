package fr.eni.clinique.ihm;

import javax.swing.*;
import java.awt.*;

public class AddClientFrame extends JInternalFrame {
    public AddClientFrame() {
		super("Ajouter un client", false, true, true,false);


		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100,500, 500);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 470, 75);
		panel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnValider = new JButton("Valider");
		btnValider.setSelectedIcon(null);
		btnValider.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/valider.png")));
		btnValider.setBounds(322, 11, 54, 53);
		panel.add(btnValider);

		JButton btnSupprimer = new JButton("Annuler");
		btnSupprimer.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/annuler.png")));
		btnSupprimer.setBounds(391, 11, 54, 53);
		panel.add(btnSupprimer);

		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(91, 137, 89, 14);
		getContentPane().add(lblNom);

		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(175, 137, 203, 20);
		getContentPane().add(textField_1);

		JLabel lblPrnom = new JLabel("Pr\u00E9nom");
		lblPrnom.setBounds(91, 187, 89, 14);
		getContentPane().add(lblPrnom);

		JTextField textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(175, 187, 203, 20);
		getContentPane().add(textField_2);

		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(91, 237, 89, 14);
		getContentPane().add(lblAdresse);

		JTextField textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(175, 237, 203, 20);
		getContentPane().add(textField_3);

		JTextField textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(175, 287, 203, 20);
		getContentPane().add(textField_4);

		JLabel lblCodePostal = new JLabel("Code Postal");
		lblCodePostal.setBounds(91, 337, 89, 14);
		getContentPane().add(lblCodePostal);

		JTextField textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(175, 337, 203, 20);
		getContentPane().add(textField_5);

		JLabel lblVille = new JLabel("Ville");
		lblVille.setBounds(91, 387, 89, 14);
		getContentPane().add(lblVille);

		JTextField textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(175, 387, 203, 20);
		getContentPane().add(textField_6);
    }
}
