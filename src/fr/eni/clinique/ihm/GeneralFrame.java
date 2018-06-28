package fr.eni.clinique.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTable;

import com.toedter.calendar.JDateChooser;



public class GeneralFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JDesktopPane desktopPane;
	private JMenuBar menuBar;
	private AgendaFrame agenda;
	private JPanel containerLogin;
	private JTable table;


	public GeneralFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1200, 720);

		// initialiser l'ecran MDI
		desktopPane = new JDesktopPane();

		// Associer le JDesktopPane à la JFrame
		setContentPane(desktopPane);

		// Barre de menus
		setJMenuBar(getMenuBarre());

		//Frame interne exemple
//		desktopPane.add(getAgenda());
		this.panelAgenda();
		
	}

	// Lancement de l'application
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				GeneralFrame ecran = new GeneralFrame();
				ecran.setVisible(true);
			}
		});

	}

	public void panelAgenda() {
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

	// Réagir aux clicks sur les menus
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
//			System.exit(0);
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
}
