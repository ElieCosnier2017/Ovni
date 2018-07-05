package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.ihm.GeneralFrame;

public class SingletonGeneral {
        //Le singleton
        private static SingletonGeneral single;
        //Variable d'instance
        private GeneralFrame name;

        //Constructeur privé
        private SingletonGeneral(){

        }

        //Méthode d'accès au singleton
        public static SingletonGeneral getInstance(){
            if(single == null)
                single = new SingletonGeneral();

            return single;
        }

        //Accesseur
        public GeneralFrame getName(){
            return this.name;
        }

        public void setName(GeneralFrame name) {
        this.name = name;
    }

        public Personnel getPersonnelGeneral() {
            return name.getpersonnel();
        }
        
}
