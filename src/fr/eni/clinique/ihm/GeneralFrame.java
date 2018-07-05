package fr.eni.clinique.ihm;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.ClientManager;
import fr.eni.clinique.bll.SingletonGeneral;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.ihm.animal.AddAnimalFrame;
import fr.eni.clinique.ihm.animal.EditAnimalFrame;
import fr.eni.clinique.ihm.animal.TableAnimalModel;
import fr.eni.clinique.ihm.client.AddClientFrame;
import fr.eni.clinique.ihm.client.RechercheFrame;
import fr.eni.clinique.ihm.personnel.AddPersonnelFrame;
import fr.eni.clinique.ihm.personnel.ResetPersonnelFrame;
import fr.eni.clinique.ihm.personnel.TablePersonnelModel;


public class GeneralFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private JDesktopPane desktopPane;
	private JMenuBar menuBar;
	private AgendaFrame agenda;
	private JTable table;
	private Personnel personnelConnect;
	private AddPersonnelFrame ajoutpersonnel;
	private ResetPersonnelFrame mdppersonnel;
	private RechercheFrame searchclient;
	private AddClientFrame addclient;
	private AddAnimalFrame addanimal;
	private EditAnimalFrame editanimal;
	private TablePersonnelModel tablePersonnelModel = new TablePersonnelModel();
	private GeneralFrame gf = this;

	public GeneralFrame(Personnel personnel) throws BLLException {
		personnelConnect = personnel;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1200, 720);

		// initialiser l'ecran MDI
		desktopPane = new JDesktopPane();

		// Associer le JDesktopPane à la JFrame
		setContentPane(desktopPane);

		// Barre de menus
		setJMenuBar(getMenuBarre());


		desktopPane.add(getAgenda());
		String role = personnel.getRole();

		if (role.equals("adm")) {
			desktopPane.add(setPersonnel());
			this.panelPersonnel();
		} else if (role.equals("sec")) {
//			this.panelRdv();
			desktopPane.add(searchClient());
			desktopPane.add(addClient());
			desktopPane.add(addAnimal());
			desktopPane.add(editAnimal());
			this.panelClient(null);
		} else if (role.equals("vet")) {
			System.out.println("Veterinaire");
			this.panelAgenda();
		}
	}

	public void panelAgenda() {
		this.setTitle("Agenda - Ani' Forme");

		JPanel panel = new JPanel();
		panel.setBounds(43, 11, 1081, 105);

		TitledBorder title;
		title = BorderFactory.createTitledBorder(" De ");
		panel.setBorder(title);

		desktopPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("V\u00E9t\u00E9rinaire :");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNewLabel.setBounds(183, 43, 79, 14);
		panel.add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(291, 35, 199, 30);
		panel.add(comboBox);

		JLabel lblNewLabel_1 = new JLabel("Date :");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNewLabel_1.setBounds(635, 43, 46, 14);
		panel.add(lblNewLabel_1);

		JDateChooser date = new JDateChooser();
		date.setBounds(691, 35, 234, 28);
		panel.add(date);

		table = new JTable();
		table.setBounds(1118, 140, -1074, 494);
		desktopPane.add(table);
	}

	public void panelPersonnel() throws BLLException {
		this.setTitle("Gestion du personnel - Ani' Forme");

		JPanel panel = new JPanel();
		panel.setBounds(43, 11, 1081, 142);
		panel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		desktopPane.add(panel);
		panel.setLayout(null);

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setActionCommand("addpersonnel");
		btnAjouter.addActionListener(this);
		btnAjouter.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/add.jpg")));
		btnAjouter.setBounds(114, 11, 120, 120);
		panel.add(btnAjouter);

		JButton btnReinitialiser = new JButton("R\u00E9initialiser");
		btnReinitialiser.setActionCommand("resetpersonnel");
		btnReinitialiser.addActionListener(this);
		btnReinitialiser.setSelectedIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/edit.png")));
		btnReinitialiser.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/edit.png")));
		btnReinitialiser.setBounds(800, 11, 120, 120);
		panel.add(btnReinitialiser);

		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setActionCommand("deletePersonnel");
		btnSupprimer.addActionListener(this);

		btnSupprimer.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/del.jpg")));
		btnSupprimer.setBounds(462, 11, 120, 120);
		panel.add(btnSupprimer);


		JLabel lblListPersonnel = new JLabel("Liste du personnel");
		lblListPersonnel.setFont(new Font("Calibri", Font.BOLD, 14));
		lblListPersonnel.setBounds(43, 170, 200, 20);
		desktopPane.add(lblListPersonnel);

		table = new JTable(new TablePersonnelModel());
		table.setShowGrid(false);
		table.setBounds(43, 200, 1081, 400);

		JScrollPane scrollPane2 = new JScrollPane(table);
		scrollPane2.setAutoscrolls(true);
		scrollPane2.setBounds(43, 200, 1081, 400);
		desktopPane.add(scrollPane2);

	}

	public void panelRdv() {
		this.setTitle("Gestion des Rendez-vous - Ani' Forme");

		JPanel panel = new JPanel();
		panel.setBounds(43, 11, 1081, 105);

		TitledBorder title;
		title = BorderFactory.createTitledBorder(" De ");
		panel.setBorder(title);

		desktopPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("V\u00E9t\u00E9rinaire :");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNewLabel.setBounds(183, 43, 79, 14);
		panel.add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(291, 35, 199, 30);
		panel.add(comboBox);

		JLabel lblNewLabel_1 = new JLabel("Date :");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNewLabel_1.setBounds(635, 43, 46, 14);
		panel.add(lblNewLabel_1);

		JDateChooser date = new JDateChooser();
		date.setBounds(691, 35, 234, 28);
		panel.add(date);

		table = new JTable();
		table.setBounds(1118, 140, -1074, 494);
		desktopPane.add(table);
	}

	public void panelClient(Client client) throws BLLException {
		this.setTitle("Gestion des Clients - Ani' Forme");

		JPanel panel = new JPanel();
		panel.setBounds(43, 11, 1081, 142);
		panel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		desktopPane.add(panel);
		panel.setLayout(null);

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setActionCommand("addclient");
		btnAjouter.addActionListener(this);
		btnAjouter.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/add.jpg")));
		btnAjouter.setBounds(425, 11, 120, 120);
		panel.add(btnAjouter);

		JButton btnValider = new JButton("Valider");
		btnValider.setSelectedIcon(null);
		btnValider.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/validerfat.png")));
		btnValider.setBounds(800, 11, 120, 120);
		panel.add(btnValider);

		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/del.jpg")));
		btnSupprimer.setBounds(569, 11, 120, 120);
		panel.add(btnSupprimer);

//		JButton btnAnnuler = new JButton("Annuler");
////		btnAnnuler.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/annuler.jpg")));
//		btnAnnuler.setBounds(934, 11, 120, 120);
//		panel.add(btnAnnuler);

		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setActionCommand("searchclient");
		btnRechercher.addActionListener(this);
		btnRechercher.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/searchfat.png")));
		btnRechercher.setBounds(25, 11, 120, 120);
		panel.add(btnRechercher);

		JPanel panel2 = new JPanel();
		panel2.setBounds(43, 200, 1081, 400);
		panel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		table = new JTable(new TableAnimalModel(client));
		table.setShowGrid(false);
		table.setBounds(43, 200, 1081, 400);

		JScrollPane scrollPane2 = new JScrollPane(table);
		scrollPane2.setAutoscrolls(true);

		scrollPane2.setBounds(485, 203, 639, 177);
		desktopPane.add(scrollPane2);

		JLabel lblListPersonnel = new JLabel("Liste des animaux du client");
		lblListPersonnel.setFont(new Font("Calibri", Font.BOLD, 14));
		lblListPersonnel.setBounds(485, 170, 200, 20);
		desktopPane.add(lblListPersonnel);

		JLabel lblCode = new JLabel("Code");
		lblCode.setBounds(74, 210, 89, 14);
		desktopPane.add(lblCode);

		JTextField textField = new JTextField();
		textField.setBounds(191, 207, 203, 20);
		desktopPane.add(textField);
		textField.setColumns(10);

		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(191, 254, 203, 20);
		desktopPane.add(textField_1);

		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(74, 257, 89, 14);
		desktopPane.add(lblNom);

		JLabel lblPrnom = new JLabel("Pr\u00E9nom");
		lblPrnom.setBounds(74, 302, 89, 14);
		desktopPane.add(lblPrnom);

		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(74, 351, 89, 14);
		desktopPane.add(lblAdresse);

		JLabel lblCodePostal = new JLabel("Code Postal");
		lblCodePostal.setBounds(74, 445, 89, 14);
		desktopPane.add(lblCodePostal);

		JLabel lblVille = new JLabel("Ville");
		lblVille.setBounds(74, 492, 89, 14);
		desktopPane.add(lblVille);

		JTextField textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(191, 348, 203, 20);
		desktopPane.add(textField_2);

		JTextField textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(191, 299, 203, 20);
		desktopPane.add(textField_3);

		JTextField textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(191, 442, 203, 20);
		desktopPane.add(textField_4);

		JTextField textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(191, 489, 203, 20);
		desktopPane.add(textField_5);

		JTextField textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(191, 395, 203, 20);
		desktopPane.add(textField_6);

		JButton btnAddAnimal = new JButton("Ajouter Animal");
		btnAddAnimal.setActionCommand("addpet");
		btnAddAnimal.addActionListener(this);
		btnAddAnimal.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/add.jpg")));
		btnAddAnimal.setBounds(814, 395, 52, 54);
		desktopPane.add(btnAddAnimal);

		JButton btnDelAnimal = new JButton("Supprimer Animal");
		btnDelAnimal.setActionCommand("delpet");
		btnDelAnimal.addActionListener(this);
		btnDelAnimal.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/del.jpg")));
		btnDelAnimal.setBounds(921, 395, 52, 54);
		desktopPane.add(btnDelAnimal);

		JButton btnEditAnimal = new JButton("Modifier Animal");
		btnEditAnimal.setActionCommand("editpet");
		btnEditAnimal.addActionListener(this);
		btnDelAnimal.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/edit.png")));
		btnEditAnimal.setBounds(1025, 395, 52, 54);
		desktopPane.add(btnEditAnimal);

		if(client != null){
			textField.setText(String.valueOf(client.getCodeClient()));
			textField_1.setText(client.getNomClient());
			textField_2.setText(client.getPrenomClient());
			textField_3.setText(client.getAdresse1());
			textField_4.setText(client.getAdresse2());
			textField_5.setText(client.getCodePostal());
			textField_6.setText(client.getVille());
		}

	}


	public void createMenuBar() {

		// Sous menu Déconnexion
		JMenu menuFichier = new JMenu("Fichier");
		menuBar.add(menuFichier);

		JMenuItem mntmDconnexion = new JMenuItem("D\u00E9connexion");
		mntmDconnexion.setActionCommand("deconnexion");
		mntmDconnexion.addActionListener(this);
		menuFichier.add(mntmDconnexion);

		JMenuItem mntmFermer = new JMenuItem("Fermer");
		mntmFermer.setActionCommand("fermer");
		mntmFermer.addActionListener(this);
		menuFichier.add(mntmFermer);

		JMenu mnNewMenu = new JMenu("Gestion des rendez-vous");
		menuBar.add(mnNewMenu);

		JMenuItem mntmPriseDeRendez = new JMenuItem("Prise de rendez vous");
		mntmPriseDeRendez.setActionCommand("setrdv");
		mntmPriseDeRendez.addActionListener(this);
		mnNewMenu.add(mntmPriseDeRendez);

		JMenuItem mntmGestionDesClients = new JMenuItem("Gestion des clients");
		mntmGestionDesClients.setActionCommand("setclient");
//		mntmGestionDesClients.addActionListener((ActionListener) this);
		mnNewMenu.add(mntmGestionDesClients);


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "deconnexion":
				System.out.println("Deconnexion");
				this.dispose();
				ConnexionFrame cnx = new ConnexionFrame();
				cnx.setVisible(true);
				break;
			case "fermer":
				System.exit(0);
				break;

			case "setrdv":
				System.out.println("setrdv");
				getAgenda().setVisible(true);
				break;

			case "addpersonnel":
				SingletonGeneral.getInstance().setName(gf);
				setPersonnel().setVisible(true);
				break;
			case "resetpersonnel":
				desktopPane.add(resetPersonnel());
				resetPersonnel().setVisible(true);
				break;

			case "deletePersonnel":
				removePersonnel();
				table.updateUI();
				break;

			case "searchclient":
				SingletonGeneral.getInstance().setName(gf);
				try {
					searchClient().setVisible(true);
				} catch (BLLException e1) {
					e1.printStackTrace();
				}
				break;

			case "addclient":
				SingletonGeneral.getInstance().setName(gf);
				addClient().setVisible(true);
				break;

			case "addpet":
				addAnimal().setVisible(true);
				break;

			case "delpet":
				System.out.println("Delete pet");
				break;

			case "editpet":
				editAnimal().setVisible(true);
				break;

			default:
				System.out.println("Probleme e=" + e);
		}
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public JMenuBar getMenuBarre() {
		if (menuBar == null) {
			menuBar = new JMenuBar();

			createMenuBar();
		}
		return menuBar;
	}

	public AgendaFrame getAgenda() {
		if(agenda == null){
			agenda = new AgendaFrame();
		}
		return agenda;
	}

	public Personnel getpersonnel(){
		return personnelConnect;
	}

	public Personnel getPersonnelSelected(){
		int selection = table.getSelectedRow();
		Personnel personnel = null;
		try {
			TablePersonnelModel tablePersonnelModel = new TablePersonnelModel();
			personnel = tablePersonnelModel.getPersonnelSelected(selection);
			if(personnel == null){
				personnel = new Personnel();
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
		return personnel;
	}

	public AddPersonnelFrame setPersonnel() {
		if(ajoutpersonnel == null) {
			ajoutpersonnel = new AddPersonnelFrame();
		}
		return ajoutpersonnel;
	}

	public ResetPersonnelFrame resetPersonnel() {
		Personnel personnel = null;
		if(mdppersonnel == null){
			personnel = new Personnel();
			mdppersonnel = new ResetPersonnelFrame(personnel);
		}else {
			personnel = getPersonnelSelected();
			mdppersonnel = new ResetPersonnelFrame(personnel);
			mdppersonnel.setVisible(true);
		}
		return mdppersonnel;
	}

	public RechercheFrame searchClient() throws BLLException {
		if(searchclient == null) {
			searchclient = new RechercheFrame();
		}
		return searchclient;
	}

	public AddClientFrame addClient() {
		if(addclient == null) {
			addclient = new AddClientFrame();
		}
		return addclient;
	}

	public AddAnimalFrame addAnimal() {
		if(addanimal == null) {
			addanimal = new AddAnimalFrame();
		}
		return addanimal;
	}

	public EditAnimalFrame editAnimal() {
		if(editanimal == null) {
			editanimal = new EditAnimalFrame();
		}
		return editanimal;
	}

	private void removePersonnel() {
		int selection = table.getSelectedRow();

		System.out.println(selection);
		try {
			if(selection >= 0) {
				tablePersonnelModel.removePersonnel(selection);
				tablePersonnelModel = new TablePersonnelModel();
				table.setModel(tablePersonnelModel);
				table.repaint();
				desktopPane.repaint();
			}

		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	public void updateListPerosnnel() throws BLLException {
		tablePersonnelModel = new TablePersonnelModel();
		table.setModel(tablePersonnelModel);
	}

	public void fillTextFieldClient(String name, String prenom){
		desktopPane.setVisible(false);
		desktopPane = new JDesktopPane();
		setContentPane(desktopPane);

		ClientManager clientManager = ClientManager.getInstance();
		try {
			Client client = clientManager.findClientByName(name);
			this.panelClient(client);
			desktopPane.add(searchClient());
			desktopPane.add(addClient());
			desktopPane.add(addAnimal());
			desktopPane.add(editAnimal());
		} catch (BLLException e) {
			e.printStackTrace();
		}


	}
}
