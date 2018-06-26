package fr.eni.clinique.bo;

public class Personnel {

    private int id;
    private String nom;
    private String mdp;
    private String role;
    private boolean archive;

    public Personnel(int id, String nom, String mdp, String role, boolean archive) {
        this.id = id;
        this.nom = nom;
        this.mdp = mdp;
        this.role = role;
        this.archive = archive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    @Override
    public String toString() {
        return "Personnel{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", mdp='" + mdp + '\'' +
                ", role='" + role + '\'' +
                ", archive=" + archive +
                '}';
    }
}

