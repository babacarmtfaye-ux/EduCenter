package com.educenter.service;

import com.educenter.dao.FormationDAO;
import com.educenter.model.Formation;

import java.sql.SQLException;
import java.util.List;

public class FormationService {

    private final FormationDAO formationDAO;

    public FormationService() {
        this.formationDAO = new FormationDAO();
    }

    public Formation ajouterFormation(String titre, int dureeJours, double prixFcfa) throws SQLException {
        if (prixFcfa <= 0) {
            throw new IllegalArgumentException("Le prix doit être strictement supérieur à 0.");
        }
        if (dureeJours <= 0) {
            throw new IllegalArgumentException("La durée doit être strictement supérieure à 0.");
        }

        Formation formation = new Formation(titre, dureeJours, prixFcfa);
        return formationDAO.ajouter(formation);
    }

    public List<Formation> listerToutesLesFormations() throws SQLException {
        return formationDAO.listerToutes();
    }

    public double calculerRevenu(int formationId) throws SQLException {
        Formation formation = formationDAO.trouverParId(formationId);
        if (formation == null) {
            throw new IllegalArgumentException("Formation introuvable pour l'ID : " + formationId);
        }

        int nombreInscrits = formationDAO.compterInscrits(formationId);
        return nombreInscrits * formation.getPrixFcfa();
    }
}