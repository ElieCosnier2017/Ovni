package fr.eni.clinique.ihm.animal;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.ClientManager;
import fr.eni.clinique.bo.Client;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAnimalFrame extends JInternalFrame implements ActionListener {
	private JTextField nom;
	private JTextField adresse;
	private JTextField adresse2;



	public AddAnimalFrame() {
		super("Ajouter un animal", false, true, true,false);


		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 470, 75);
		panel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnValider = new JButton("Valider");
		btnValider.setActionCommand("Valider");
		btnValider.addActionListener(this);
		btnValider.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/valider.png")));
		btnValider.setBounds(322, 11, 54, 53);
		panel.add(btnValider);

		JButton btnSupprimer = new JButton("Annuler");
		btnSupprimer.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/annuler.png")));
		btnSupprimer.setBounds(391, 11, 54, 53);
		panel.add(btnSupprimer);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		panel_1.setBounds(10, 97, 470, 58);
		getContentPane().add(panel_1);

		TitledBorder title;
		title = BorderFactory.createTitledBorder(" Client : ");
		panel_1.setBorder(title);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		panel_2.setBounds(24, 22, 423, 25);
		panel_1.add(panel_2);

		JLabel lblNewLabel = new JLabel("Client 1");
		lblNewLabel.setBounds(10, 0, 331, 25);
		panel_2.add(lblNewLabel);

		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(32, 166, 89, 14);
		getContentPane().add(lblNom);

		nom = new JTextField();
		nom.setColumns(10);
		nom.setBounds(113, 163, 203, 20);
		getContentPane().add(nom);

		JLabel lblAdresse = new JLabel("Couleur");
		lblAdresse.setBounds(32, 213, 68, 14);
		getContentPane().add(lblAdresse);

		adresse = new JTextField();
		adresse.setColumns(10);
		adresse.setBounds(113, 210, 203, 20);
		getContentPane().add(adresse);

		adresse2 = new JTextField();
		adresse2.setColumns(10);
		adresse2.setBounds(113, 306, 203, 20);
		getContentPane().add(adresse2);

		JLabel lblCodePostal = new JLabel("Esp\u00E8ce");
		lblCodePostal.setBounds(32, 257, 89, 14);
		getContentPane().add(lblCodePostal);

		JLabel lblVille = new JLabel("Race");
		lblVille.setBounds(269, 257, 59, 14);
		getContentPane().add(lblVille);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(350, 163, 105, 20);
		getContentPane().add(comboBox);

		JLabel lblTatouage = new JLabel("Tatouage");
		lblTatouage.setBounds(32, 309, 89, 14);
		getContentPane().add(lblTatouage);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(113, 257, 130, 22);
		getContentPane().add(comboBox_1);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(325, 254, 130, 22);
		getContentPane().add(comboBox_2);


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "Valider":
				ajouterClient();
				break;
			default:
				System.out.println("Probleme e=" + e);
		}
	}

	private void ajouterClient() {
//    	String name = nom.getText();
//    	String adress = adresse.getText();
//    	String adress2 = adresse2.getText();
//
//    	System.out.println("yo");
//
//    	ClientManager clientManager = ClientManager.getInstance();
//		try {
//		} catch (BLLException e) {
//			e.printStackTrace();
//		}

	}
}
