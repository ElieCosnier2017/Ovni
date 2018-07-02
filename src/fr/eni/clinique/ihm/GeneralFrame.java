package fr.eni.clinique.ihm;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Clock;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bo.Personnel;


public class GeneralFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JDesktopPane desktopPane;
	private JMenuBar menuBar;
	private AgendaFrame agenda;
	private JTable table;
	private AddPersonnelFrame personnel;
	private ResetPersonnelFrame mdppersonnel;

	public GeneralFrame(Personnel personnel) throws BLLException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1200, 720);

		// initialiser l'ecran MDI
		desktopPane = new JDesktopPane();

		// Associer le JDesktopPane à la JFrame
		setContentPane(desktopPane);

		// Barre de menus
		setJMenuBar(getMenuBarre());

		//Frame interne exemple
		desktopPane.add(setPersonnel());
		desktopPane.add(resetPersonnel());
		desktopPane.add(getAgenda());
		String role = personnel.getRole();

		if (role.equals("adm")) {
			System.out.println("administrateur");
			this.panelPersonnel();
		} else if (role.equals("sec")) {
			System.out.println("Secretaire");
//			this.panelRdv();
			this.panelClient();
		} else if (role.equals("vet")) {
			System.out.println("Veterinaire");
			this.panelAgenda();
		}

		
	}

//	// Lancement de l'application
////	public static void main(String[] args) {
////		SwingUtilities.invokeLater(new Runnable() {
////
//////			@Override
//////			public void run() {
//////				GeneralFrame ecran = new GeneralFrame();
//////				ecran.setVisible(true);
//////			}
////		});
////
////	}

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

        JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setActionCommand("addpersonnel");
		btnNewButton.addActionListener(this);
        btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/add.jpg")));
        btnNewButton.setBounds(114, 11, 120, 120);
        panel.add(btnNewButton);

        JButton btnRinitialiser = new JButton("R\u00E9initialiser");
		btnRinitialiser.setActionCommand("resetpersonnel");
		btnRinitialiser.addActionListener(this);
        btnRinitialiser.setSelectedIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/edit.png")));
        btnRinitialiser.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/edit.png")));
        btnRinitialiser.setBounds(800, 11, 120, 120);
        panel.add(btnRinitialiser);

        JButton btnSupprimer = new JButton("Supprimer");
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


	public void panelClient() {
		this.setTitle("Gestion des Clients - Ani' Forme");

		JPanel panel = new JPanel();
		panel.setBounds(43, 11, 1081, 142);
		panel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		desktopPane.add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/add.jpg")));
		btnNewButton.setBounds(425, 11, 120, 120);
		panel.add(btnNewButton);

		JButton btnRinitialiser = new JButton("Valider");
		btnRinitialiser.setSelectedIcon(null);
		btnRinitialiser.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/edit.png")));
		btnRinitialiser.setBounds(800, 11, 120, 120);
		panel.add(btnRinitialiser);

		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/del.jpg")));
		btnSupprimer.setBounds(569, 11, 120, 120);
		panel.add(btnSupprimer);

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(934, 11, 120, 120);
		panel.add(btnAnnuler);

		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(25, 11, 120, 120);
		panel.add(btnRechercher);

		JPanel panel2 = new JPanel();
		panel2.setBounds(43, 200, 1081, 400);
		panel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		table = new JTable(new TableClientModel());
		table.setShowGrid(false);
		table.setBounds(43, 200, 1081, 400);

		JScrollPane scrollPane2 = new JScrollPane(table);
		scrollPane2.setAutoscrolls(true);

		scrollPane2.setBounds(485, 203, 639, 177);
		desktopPane.add(scrollPane2);

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

		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(814, 395, 71, 64);
		desktopPane.add(btnNewButton_1);

		JButton button = new JButton("New button");
		button.setBounds(921, 395, 71, 64);
		desktopPane.add(button);

		JButton button_1 = new JButton("New button");
		button_1.setBounds(1025, 395, 71, 64);
		desktopPane.add(button_1);


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
			System.out.println("addclient");
			setPersonnel().setVisible(true);
			break;

		case "resetpersonnel":
			resetPersonnel().setVisible(true);
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

	public AddPersonnelFrame setPersonnel() {
		if(personnel == null) {
			personnel = new AddPersonnelFrame();
		}
		return personnel;
	}

	public ResetPersonnelFrame resetPersonnel() {
		if(mdppersonnel == null) {
			mdppersonnel = new ResetPersonnelFrame();
		}
		return mdppersonnel;
	}
}
