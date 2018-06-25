package fr.eni.clinique.bo;

public class Animal {

    public static final char[] SEXE = {'M', 'F', 'H'};
    private String race;
    private String espece;
    private Client client;
    private int codeAnimal;
    private String nomAnimal;
    private char sexe;
    private boolean archive;
    private String couleur;
    private long codeClient;
    private String tatouage;
    private String antecedents;

    public Animal(String race, String espece, Client client, int codeAnimal, String nomAnimal, char sexe, boolean archive, String couleur, long codeClient, String tatouage, String antecedents) {
        this.race = race;
        this.espece = espece;
        this.client = client;
        this.codeAnimal = codeAnimal;
        this.nomAnimal = nomAnimal;
        this.sexe = sexe;
        this.archive = archive;
        this.couleur = couleur;
        this.codeClient = codeClient;
        this.tatouage = tatouage;
        this.antecedents = antecedents;
    }

    public static char[] getSEXE() {
        return SEXE;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getEspece() {
        return espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getCodeAnimal() {
        return codeAnimal;
    }

    public void setCodeAnimal(int codeAnimal) {
        this.codeAnimal = codeAnimal;
    }

    public String getNomAnimal() {
        return nomAnimal;
    }

    public void setNomAnimal(String nomAnimal) {
        this.nomAnimal = nomAnimal;
    }

    public char getSexe() {
        return sexe;
    }

    public void setSexe(char sexe) {
        this.sexe = sexe;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public long getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(long codeClient) {
        this.codeClient = codeClient;
    }

    public String getTatouage() {
        return tatouage;
    }

    public void setTatouage(String tatouage) {
        this.tatouage = tatouage;
    }

    public String getAntecedents() {
        return antecedents;
    }

    public void setAntecedents(String antecedents) {
        this.antecedents = antecedents;
    }
}
