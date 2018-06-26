package fr.eni.clinique.bo;

import java.util.Date;

public class Rdv {

    private Client client;
    private Personnel pers;
    private Date date;
    private Animal animal;

    public Rdv(Client client, Personnel pers, Date date, Animal animal) {
        this.client = client;
        this.pers = pers;
        this.date = date;
        this.animal = animal;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Personnel getPers() {
        return pers;
    }

    public void setPers(Personnel pers) {
        this.pers = pers;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "Rdv{" +
                "client=" + client +
                ", pers=" + pers +
                ", date=" + date +
                ", animal=" + animal +
                '}';
    }
}
