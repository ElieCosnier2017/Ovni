package fr.eni.clinique.ihm.animal;

import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.ClientManager;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAnimalFrame extends JInternalFrame implements ActionListener {
	private JLabel lblNomClient;
	private Integer codeClient;
    private JTextField nom;
    private JComboBox sexe;
    private JTextField couleur;
    private JComboBox espece;
    private JComboBox race;
    private JTextField tatouage;
	private JTextField adresse2;
	private String nomCli = "null";

	public AddAnimalFrame(String infoClient) {
		super("Ajouter un animal", false, true, true, false);

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

		lblNomClient = new JLabel("Client 1");
		lblNomClient.setBounds(10, 0, 331, 25);
		panel_2.add(lblNomClient);

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

		couleur = new JTextField();
		couleur.setColumns(10);
		couleur.setBounds(113, 210, 203, 20);
		getContentPane().add(couleur);

		tatouage = new JTextField();
		tatouage.setColumns(10);
		tatouage.setBounds(113, 306, 203, 20);
		getContentPane().add(tatouage);

		JLabel lblCodePostal = new JLabel("Esp\u00E8ce");
		lblCodePostal.setBounds(32, 257, 89, 14);
		getContentPane().add(lblCodePostal);

		JLabel lblVille = new JLabel("Race");
		lblVille.setBounds(269, 257, 59, 14);
		getContentPane().add(lblVille);

		sexe = new JComboBox();
		sexe.setBounds(350, 163, 105, 20);
		sexe.addItem("M");
		sexe.addItem("F");
		sexe.addItem("H");
		getContentPane().add(sexe);

		JLabel lblTatouage = new JLabel("Tatouage");
		lblTatouage.setBounds(32, 309, 89, 14);
		getContentPane().add(lblTatouage);

		espece = new JComboBox();
		espece.setBounds(113, 257, 130, 22);
		espece.addItem("Chat");
        espece.addItem("Cheval");
        espece.addItem("Chien");
        espece.addItem("Crocodile");
        espece.addItem("Vache");
		getContentPane().add(espece);

		race = new JComboBox();
		race.setBounds(325, 254, 130, 22);
        race.addItem("chat gentil");
        race.addItem("chat méchant");
        race.addItem("cheval gentil");
        race.addItem("cheval méchant");
        race.addItem("chien con-con");
        race.addItem("chien malin");
        race.addItem("chien méchant");
        race.addItem("chien pervers");
        race.addItem("crocodile gentil");
        race.addItem("crocodile méchant");
        race.addItem("vache gentilel");
        race.addItem("vache gentile");
        race.addItem("chat gentil");
		getContentPane().add(race);

		if(infoClient.equals("null")){
		    lblNomClient.setText("null");
        }else {
            String[] listinfo = infoClient.split(" ");
            System.out.println(listinfo[0]);
            System.out.println(listinfo[1]);
            System.out.println(listinfo[2]);
            codeClient = Integer.parseInt(listinfo[0]);
            nomCli = listinfo[1];
            lblNomClient.setText(listinfo[1] + " " + listinfo[2]);
        }

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "Valider":
				ajouterAnimal();
				break;
			default:
				System.out.println("Probleme e=" + e);
		}
	}

	private void ajouterAnimal() {
		try {
		    if(codeClient != null){
                Animal animal = new Animal(nom.getText(),sexe.getSelectedItem().toString(),couleur.getText(),race.getSelectedItem().toString(),espece.getSelectedItem().toString(),Long.valueOf(codeClient),tatouage.getText(),"",false);
                AnimalManager animalManager = AnimalManager.getInstance();
                animalManager.insertAnimal(animal);
            }

		} catch (BLLException e) {
			e.printStackTrace();
		}

	}

	public void refreshFrame(String infosClient){
        String[] listinfoClient = infosClient.split(" ");
        System.out.println("CODE = " + listinfoClient[0]);
        System.out.println("NOM = " + listinfoClient[1]);
        System.out.println("PRENOM = " + listinfoClient[2]);
        lblNomClient.setText(listinfoClient[1] + " " + listinfoClient[2]);
        codeClient = Integer.parseInt(listinfoClient[0]);
    }
}
