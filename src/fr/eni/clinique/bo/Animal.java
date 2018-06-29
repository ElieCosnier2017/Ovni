package fr.eni.clinique.bo;

public class Animal {

    private Integer codeAnimal;
    private String nomAnimal;
    private static Character[] sexe = {'M', 'F', 'H'};
    private String couleur;
    private String race;
    private String espece;
    private Long codeClient;
    private String tatouage;
    private String antecedents;
    private Boolean archive;

    public Animal(Integer codeAnimal, String nomAnimal, Character[] sexe, String couleur, String race, String espece, Long codeClient, String tatouage, String antecedents, Boolean archive) {
        this.codeAnimal = codeAnimal;
        this.nomAnimal = nomAnimal;
        Animal.sexe = sexe;
        this.couleur = couleur;
        this.race = race;
        this.espece = espece;
        this.codeClient = codeClient;
        this.tatouage = tatouage;
        this.antecedents = antecedents;
        this.archive = archive;
    }

    public Integer getCodeAnimal() {
        return codeAnimal;
    }

    public void setCodeAnimal(Integer codeAnimal) {
        this.codeAnimal = codeAnimal;
    }

    public String getNomAnimal() {
        return nomAnimal;
    }

    public void setNomAnimal(String nomAnimal) {
        this.nomAnimal = nomAnimal;
    }

    public static Character[] getSexe() {
        return sexe;
    }

    public static void setSexe(Character[] sexe) {
        Animal.sexe = sexe;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
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

    public Long getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(Long codeClient) {
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

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "codeAnimal=" + codeAnimal +
                ", nomAnimal='" + nomAnimal + '\'' +
                ", couleur='" + couleur + '\'' +
                ", race='" + race + '\'' +
                ", espece='" + espece + '\'' +
                ", codeClient=" + codeClient +
                ", tatouage='" + tatouage + '\'' +
                ", antecedents='" + antecedents + '\'' +
                ", archive=" + archive +
                '}';
    }
}