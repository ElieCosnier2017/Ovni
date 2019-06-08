package fr.eni.clinique.bo;

import java.util.Date;

public class Rdv {

    private Integer CodeVeto;
    private Date DateRdv;
    private Integer CodeAnimal;

    public Rdv(Integer codeVeto, Date dateRdv, Integer codeAnimal) {
        CodeVeto = codeVeto;
        DateRdv = dateRdv;
        CodeAnimal = codeAnimal;
    }

    public Integer getCodeVeto() {
        return CodeVeto;
    }

    public void setCodeVeto(Integer codeVeto) {
        CodeVeto = codeVeto;
    }

    public Date getDateRdv() {
        return DateRdv;
    }

    public void setDateRdv(Date dateRdv) {
        DateRdv = dateRdv;
    }

    public Integer getCodeAnimal() {
        return CodeAnimal;
    }

    public void setCodeAnimal(Integer codeAnimal) {
        CodeAnimal = codeAnimal;
    }

    @Override
    public String toString() {
        return "Rdv{" +
                "CodeVeto=" + CodeVeto +
                ", DateRdv=" + DateRdv +
                ", CodeAnimal=" + CodeAnimal +
                '}';
    }
}
