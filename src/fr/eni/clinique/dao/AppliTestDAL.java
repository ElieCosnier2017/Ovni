package fr.eni.clinique.dao;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.Rdv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AppliTestDAL {


    public static void main(String[] args) throws DALException {
        ClientDAO clientDAO = DAOfactory.getClientDao();
        PersonnelDAO personnelDAO = DAOfactory.getPersonnelDao();
        RdvDAO rdvDAO = DAOfactory.getRdvDao();
        AnimalDAO animalDAO = DAOfactory.getAnimalDao();

        Client c1 = new Client("Gonzales", "Pedro","10 rue de la banane", "11 rue de la frite", "35000",
                "Paris", "0123456789", "Oui","jean@lafritte.com","Trop Cute", true );
        Client c2 = new Client("Fransisco", "Jean-Maurice","10 rue de la courgette", "11 rue de la patate", "35000",
                "Paris", "0123456789", "non","jean@diddle.com","Pauvre", true );

        Personnel p1 = new Personnel("Juan","tacos","vet",true);
        Personnel p2 = new Personnel("Cornichon", "bonsoir", "adm", false);
        Personnel p3 = new Personnel("RedBull", "monster", "sec", false);
        String date1 = "22/06/2006";
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = simpleDateFormat.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Rdv rdv = new Rdv(1,date,2);

        Animal a1 = new Animal("YEAHBOIIIIIIIIIIIIIIIIII", 'M', "Bleu","juif", "Chien", c1.getCodeClient().longValue(),"112145455","aucun", true);

        try {

            //CLIENTS -------------------
            System.out.println("Ajout de clients : ----");
            clientDAO.insert(c1);
            System.out.println(c1.toString());
            clientDAO.insert(c2);
            System.out.println(c2.toString());
            System.out.println("Fin Ajout clients : ----");

            System.out.println("Update de clients : ----");
            c1.setRemarque("Aime les chats");
            clientDAO.update(c1);
            System.out.println("Fin update");

            System.out.println("Selection de tous les clients : ");
            List<Client> clientList = clientDAO.selectAll();
            System.out.println(clientList.toString());
            System.out.println("Nombre de Clients : " + clientList.size());

            System.out.println("Fin Selection de tous les clients : ");

            System.out.println("Selection de un client : ");
            Client client = clientDAO.selectOne(c1.getCodeClient());
            System.out.println(client.toString());
            System.out.println("Fin Selection de un client.");

            System.out.println("Suppression de un client : ");
            clientDAO.delete(c1);
            System.out.println("Fin supression de un client : ");

            System.out.println("Selection de tous les clients suite a suppression : ");
            List<Client> clientListSupr = clientDAO.selectAll();
            for(Client client1 : clientListSupr){
                System.out.println(client1.getCodeClient() + " " + client1.toString());
            }
            System.out.println("Fin Selection de tous les clients  suite a suppression: ");

            //PERSONNELS ----------------
            System.out.println("Ajout de personnel : ----");
            personnelDAO.insert(p1);
            System.out.println(p1.toString());
            personnelDAO.insert(p2);
            System.out.println(p2.toString());
            personnelDAO.insert(p3);
            System.out.println(p3.toString());
            System.out.println("Fin Ajout personnel : ----");

            System.out.println("Update de clients : ----");
            p1.setNom("XXXTentacion");
            personnelDAO.update(p1);
            System.out.println("Fin update");

            System.out.println("Selection de tous le personnel : ");
            List<Personnel> personnelList = personnelDAO.selectAll();
            System.out.println(personnelList.toString());
            System.out.println("Nombre de personnels : " + personnelList.size());

            System.out.println("Fin Selection de tous le personnel : ");

            System.out.println("Selection de un personnel : ");
            Personnel personnel = personnelDAO.selectOne(p1.getId());
            System.out.println(personnel.toString());
            System.out.println("Fin Selection de un personnel.");

            System.out.println("Suppression de un personnel : ");
            personnelDAO.delete(p1);
            System.out.println("Fin supression de un personnel : ");

            System.out.println("Selection de tous les clients suite a suppression : ");
            List<Personnel> personnelListSupr = personnelDAO.selectAll();
            for(Personnel personnel1 : personnelListSupr){
                System.out.println(personnel1.getId() + " " + personnel1.toString());
            }
            System.out.println("Fin Selection de tous le personnel  suite a suppression: ");

            //RDV ---------------------
            System.out.println("Ajout de Rendez-Vous : ----");
            rdvDAO.insert(rdv);
            System.out.println(rdv.toString());

            System.out.println("Fin Ajout Rendez-Vous : ----");

            System.out.println("Update de Rendez-Vous : ----");
            rdv.setCodeVeto(1);
            rdvDAO.update(rdv);
            System.out.println("Fin update");

            System.out.println("Selection de tous le  Rendez-Vous d'un vet : ");
            List<Rdv> rdvList = rdvDAO.getListRdvByVet(p1.getId());
            System.out.println(rdvList.toString());
            System.out.println("Nombre de  Rendez-Vous : " + rdvList.size());

            System.out.println("Fin Selection de tous le  Rendez-Vous : ");


            //Animal
            System.out.println("Ajout de Animal : ----");
            animalDAO.insert(a1);
            System.out.println(a1.toString());
            System.out.println("Fin Ajout Animal : ----");

            System.out.println("Update de Animal : ----");
            a1.setNomAnimal("XXXTentacion");
            animalDAO.update(a1);
            System.out.println("Fin update");

//            System.out.println("Selection de tous le Animal : ");
//            List<Animal> animalList = AnimalDAO.selectAll();
//            System.out.println(personnelList.toString());
//            System.out.println("Nombre de Animal : " + personnelList.size());
//
//            System.out.println("Fin Selection de tous le Animal : ");
//
//            System.out.println("Selection de un Animal : ");
//            Personnel personnel = personnelDAO.selectOne(p1.getId());
//            System.out.println(personnel.toString());
//            System.out.println("Fin Selection de un Animal.");
//
//            System.out.println("Suppression de un Animal : ");
//            personnelDAO.delete(p1);
//            System.out.println("Fin supression de un Animal : ");
//
//            System.out.println("Selection de tous les clients suite a suppression : ");
//            List<Personnel> personnelListSupr = personnelDAO.selectAll();
//            for(Personnel personnel1 : personnelListSupr){
//                System.out.println(personnel1.getId() + " " + personnel1.toString());
//            }
//            System.out.println("Fin Selection de tous le personnel  suite a suppression: ");
            
        } catch (DALException e){
            throw new DALException("Erreur DAL", e);
        }
    }
}
