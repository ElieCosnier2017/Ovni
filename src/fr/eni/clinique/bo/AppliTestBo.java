package fr.eni.clinique.bo;

import fr.eni.clinique.dao.DALException;

import java.util.Objects;

public class AppliTestBo {
    public static void main(String[] args) throws DALException {

        //Client
        //Test creation + getter & setter
         Integer codeClient = 1;
         String nomClient = "test";
         String prenomClient = "test";
         String adresse = "adresseTest";
         String adresse2 = "suiteAdresse";
         String codePostal = "12345";
         String ville = "testCity";
         String numTel = "0123456789";
         String assurance ="123456";
         String email = "test@test.fr";
         String remarque = "aucune remarque";
         Boolean archive = false;

         //setter
        Client client = new Client();
        client.setNomClient(nomClient);
        client.setPrenomClient(prenomClient);
        client.setAdresse1(adresse);
        client.setAdresse2(adresse2);
        client.setCodePostal(codePostal);
        client.setVille(ville);
        client.setNumTel(numTel);
        client.setAssurance(assurance);
        client.setEmail(email);
        client.setRemarque(remarque);
        client.setArchive(archive);

        //getter
        Client client2 = new Client();
        client2.setNomClient(client.getNomClient());
        client2.setPrenomClient(client.getPrenomClient());
        client2.setAdresse1(client.getAdresse1());
        client2.setAdresse2(client.getAdresse2());
        client2.setCodePostal(client.getCodePostal());
        client2.setVille(client.getVille());
        client2.setNumTel(client.getNumTel());
        client2.setAssurance(client.getAssurance());
        client2.setEmail(client.getEmail());
        client2.setRemarque(client.getRemarque());
        client2.setArchive(client.isArchive());

        Client client3 = new Client(nomClient,prenomClient,adresse,adresse2,codePostal,ville,numTel,assurance,email,remarque,archive);

        if(client.toString().equals(client2.toString()) && client2.toString().equals(client3.toString()) && client.toString().equals(client3.toString()))
            System.out.println("Client OK");
        else
            System.out.println("Client KO");

        //Personnel
        String nom = "test";
        String mdp = "test";
        String role = "adm";
        Boolean archivePers = false;

        //setter
        Personnel personnel = new Personnel();
        personnel.setNom(nom);
        personnel.setMdp(mdp);
        personnel.setRole(role);
        personnel.setArchive(archivePers);

        //getter
        Personnel personnel2 = new Personnel();
        personnel2.setNom(personnel.getNom());
        personnel2.setMdp(personnel.getMdp());
        personnel2.setRole(personnel.getRole());
        personnel2.setArchive(personnel.isArchive());

        Personnel personnel3 = new Personnel(nom,mdp,role,archivePers);

        if(personnel.toString().equals(personnel2.toString()) && personnel2.toString().equals(personnel3.toString()) && personnel.toString().equals(personnel3.toString()))
            System.out.println("Personnel OK");
        else
            System.out.println("Personnel KO");


        //Animal
         Integer codeAnimal;
         String nomAnimal = "test";
         String sexe = "F";
         String couleur = "test";
         String race = "chien gentil";
         String espece = "Chien";
         Long codeClientAni = Long.valueOf(1234);
         String tatouage = "123456";
         String antecedents = "";
         Boolean archiveAni = false;

         //setter
        Animal animal = new Animal();
        animal.setNomAnimal(nomAnimal);
        animal.setSexe(sexe);
        animal.setCouleur(couleur);
        animal.setRace(race);
        animal.setEspece(espece);
        animal.setCodeClient(codeClientAni);
        animal.setTatouage(tatouage);
        animal.setAntecedents(antecedents);
        animal.setArchive(archiveAni);

        //getter
        Animal animal2 = new Animal();
        animal2.setNomAnimal(animal.getNomAnimal());
        animal2.setSexe(animal.getSexe());
        animal2.setCouleur(animal.getCouleur());
        animal2.setRace(animal.getRace());
        animal2.setEspece(animal.getEspece());
        animal2.setCodeClient(animal.getCodeClient());
        animal2.setTatouage(animal.getTatouage());
        animal2.setAntecedents(animal.getAntecedents());
        animal2.setArchive(animal.getArchive());

        Animal animal3 = new Animal(nomAnimal,sexe, couleur,race,espece,codeClientAni,tatouage,antecedents,archiveAni);

        if(animal.toString().equals(animal2.toString()) && animal2.toString().equals(animal3.toString()) && animal.toString().equals(animal3.toString()))
            System.out.println("Animal OK");
        else
            System.out.println("Animal KO");

    }
}
