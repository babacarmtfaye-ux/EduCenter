package com.educenter.service;

import com.educenter.dao.EtudiantDAO;
import com.educenter.model.Etudiant;

import java.sql.SQLException;
import java.util.List;

public class EtudiantService {

    private final EtudiantDAO etudiantDAO;

    public EtudiantService() {
        this.etudiantDAO = new EtudiantDAO();
    }

    public Etudiant ajouterEtudiant(String nom, String prenom, String email) throws SQLException {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("L'e-mail doit contenir un '@'.");
        }

        Etudiant etudiant = new Etudiant(nom, prenom, email);
        return etudiantDAO.ajouter(etudiant);
    }

    public List<Etudiant> listerTousLesEtudiants() throws SQLException {
        return etudiantDAO.listerTous();
    }
}