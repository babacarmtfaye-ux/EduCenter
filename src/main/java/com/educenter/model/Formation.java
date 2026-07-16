package com.educenter.model;

public class Formation {

    private int id;
    private String titre;
    private int dureeJours;
    private double prixFcfa;

    public Formation() {
    }

    public Formation(String titre, int dureeJours, double prixFcfa) {
        this.titre = titre;
        this.dureeJours = dureeJours;
        this.prixFcfa = prixFcfa;
    }

    public Formation(int id, String titre, int dureeJours, double prixFcfa) {
        this.id = id;
        this.titre = titre;
        this.dureeJours = dureeJours;
        this.prixFcfa = prixFcfa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getDureeJours() {
        return dureeJours;
    }

    public void setDureeJours(int dureeJours) {
        this.dureeJours = dureeJours;
    }

    public double getPrixFcfa() {
        return prixFcfa;
    }

    public void setPrixFcfa(double prixFcfa) {
        this.prixFcfa = prixFcfa;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + titre + " (" + dureeJours + " jours) - " + prixFcfa + " FCFA";
    }
}