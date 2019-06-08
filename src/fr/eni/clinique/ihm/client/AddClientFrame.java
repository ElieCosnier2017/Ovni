package fr.eni.clinique.ihm.client;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.ClientManager;
import fr.eni.clinique.bll.SingletonGeneral;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.ihm.GeneralFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClientFrame extends JInternalFrame implements ActionListener {

	private JTextField nom;
	private JTextField prenom;
	private JTextField adresse;
	private JTextField adresse2;
	private JTextField codepostal;
	private JTextField ville;

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
		btnValider.setActionCommand("Valider");
		btnValider.addActionListener(this);
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

		nom = new JTextField();
		nom.setColumns(10);
		nom.setBounds(175, 137, 203, 20);
		getContentPane().add(nom);

		JLabel lblPrnom = new JLabel("Pr\u00E9nom");
		lblPrnom.setBounds(91, 187, 89, 14);
		getContentPane().add(lblPrnom);

		prenom = new JTextField();
		prenom.setColumns(10);
		prenom.setBounds(175, 187, 203, 20);
		getContentPane().add(prenom);

		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(91, 237, 89, 14);
		getContentPane().add(lblAdresse);

		adresse = new JTextField();
		adresse.setColumns(10);
		adresse.setBounds(175, 237, 203, 20);
		getContentPane().add(adresse);

		adresse2 = new JTextField();
		adresse2.setColumns(10);
		adresse2.setBounds(175, 287, 203, 20);
		getContentPane().add(adresse2);

		JLabel lblCodePostal = new JLabel("Code Postal");
		lblCodePostal.setBounds(91, 337, 89, 14);
		getContentPane().add(lblCodePostal);

		codepostal = new JTextField();
		codepostal.setColumns(10);
		codepostal.setBounds(175, 337, 203, 20);
		getContentPane().add(codepostal);

		JLabel lblVille = new JLabel("Ville");
		lblVille.setBounds(91, 387, 89, 14);
		getContentPane().add(lblVille);

		ville = new JTextField();
		ville.setColumns(10);
		ville.setBounds(175, 387, 203, 20);
		getContentPane().add(ville);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "Valider":
				ajouterClient();
				this.dispose();
				break;
			default:
				System.out.println("Probleme e=" + e);
		}
		try {
			GeneralFrame ecran = new GeneralFrame(SingletonGeneral.getInstance().getPersonnelGeneral());
			ecran.setVisible(true);
		} catch (BLLException e1) {
			e1.printStackTrace();
		}
		SingletonGeneral.getInstance().getName().dispose();
	}

	private void ajouterClient() {
    	String name = nom.getText();
    	String firstname = prenom.getText();
    	String adress = adresse.getText();
    	String adress2 = adresse2.getText();
    	String postalcode = codepostal.getText();
    	String city = ville.getText();

		Client client = new Client(name,firstname,adress,adress2,postalcode,city,null,null,null,null,false);

    	ClientManager clientManager = ClientManager.getInstance();
		try {
			clientManager.insertClient(client);
		} catch (BLLException e) {
			e.printStackTrace();
		}

	}
}
