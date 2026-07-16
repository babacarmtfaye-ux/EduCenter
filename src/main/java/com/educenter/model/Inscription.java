package com.educenter.model;

import java.time.LocalDate;

public class Inscription {

    private int id;
    private int etudiantId;
    private int formationId;
    private LocalDate dateInscription;

    public Inscription() {
    }

    public Inscription(int etudiantId, int formationId, LocalDate dateInscription) {
        this.etudiantId = etudiantId;
        this.formationId = formationId;
        this.dateInscription = dateInscription;
    }

    public Inscription(int id, int etudiantId, int formationId, LocalDate dateInscription) {
        this.id = id;
        this.etudiantId = etudiantId;
        this.formationId = formationId;
        this.dateInscription = dateInscription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(int etudiantId) {
        this.etudiantId = etudiantId;
    }

    public int getFormationId() {
        return formationId;
    }

    public void setFormationId(int formationId) {
        this.formationId = formationId;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    @Override
    public String toString() {
        return "Inscription[" + id + "] Etudiant=" + etudiantId + ", Formation=" + formationId + ", Date=" + dateInscription;
    }
}