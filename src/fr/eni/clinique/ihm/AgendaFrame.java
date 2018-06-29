package fr.eni.clinique.ihm;

import javax.swing.JInternalFrame;

public class AgendaFrame extends JInternalFrame {

    public AgendaFrame() {
        //Ecran avec un titre, redimensionable, fermable, agrandissable, iconifiable
        super("Titre Frame 1", true, true, true,true);

        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        setBounds(100, 100,400, 200);

    }

}
