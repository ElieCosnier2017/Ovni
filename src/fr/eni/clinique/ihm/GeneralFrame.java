package fr.eni.clinique.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;

public class GeneralFrame extends JFrame {

	private JPanel contentPane;
	private static GeneralFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GeneralFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GeneralFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, 1200, 720);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuFichier = new JMenu("Fichier");
		menuBar.add(menuFichier);
		
		JMenuItem mntmDconnexion = new JMenuItem("D\u00E9connexion");
		mntmDconnexion.setActionCommand("deconnexion");
		mntmDconnexion.addActionListener(new actionApp());
		menuFichier.add(mntmDconnexion);
		
		JMenuItem mntmFermer = new JMenuItem("Fermer");
		mntmFermer.setActionCommand("fermer");
		mntmFermer.addActionListener(new actionApp());
		menuFichier.add(mntmFermer);
		
		JMenu mnNewMenu = new JMenu("Gestion des rendez-vous");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmPriseDeRendez = new JMenuItem("Prise de rendez vous");
		mntmPriseDeRendez.setActionCommand("getrdv");
//		mntmPriseDeRendez.addActionListener((ActionListener) this);
		mnNewMenu.add(mntmPriseDeRendez);
		
		JMenuItem mntmGestionDesClients = new JMenuItem("Gestion des clients");
		mntmGestionDesClients.setActionCommand("setclient");
//		mntmGestionDesClients.addActionListener((ActionListener) this);
		mnNewMenu.add(mntmGestionDesClients);
		
		JMenu mnAgenda = new JMenu("Agenda");
		mnAgenda.setActionCommand("agenda");
//		mnAgenda.addActionListener((ActionListener) this);
		menuBar.add(mnAgenda);
		
		JMenu mnGestionDuPersonnel = new JMenu("Gestion du personnel");
		mnGestionDuPersonnel.setActionCommand("setpersonnel");
//		mnGestionDuPersonnel.addActionListener((ActionListener) this);
		menuBar.add(mnGestionDuPersonnel);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.NORTH);
	}
	
    static class actionApp implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "deconnexion":
				System.out.println("Deconnexion");
				frame.dispose();
		      	ConnexionFrame cnx = new ConnexionFrame();
				cnx.setVisible(true);    
				break;
			case "fermer":
				System.exit(0);
				break;
	
			case "ecran":
				System.out.println("ecran");
	//			getFrm1().setVisible(true);
				break;
	
			default:
				System.out.println("Probleme e=" + e);
			}
	
		}
    }

//	public InternalFrame1 getFrm1() {
//		IF(FRM1== NULL){
//			FRM1 = NEW INTERNALFRAME1();
//		}
//		RETURN FRM1;
//	}
}
