package com.educenter.service;

import com.educenter.dao.InscriptionDAO;
import com.educenter.model.Etudiant;
import com.educenter.model.Inscription;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class InscriptionService {

    private final InscriptionDAO inscriptionDAO;

    public InscriptionService() {
        this.inscriptionDAO = new InscriptionDAO();
    }

    public Inscription inscrireEtudiant(int etudiantId, int formationId) throws SQLException {
        boolean dejaInscrit = inscriptionDAO.existeInscription(etudiantId, formationId);

        if (dejaInscrit) {
            throw new IllegalStateException("Cet étudiant est déjà inscrit à cette formation.");
        }

        Inscription inscription = new Inscription(etudiantId, formationId, LocalDate.now());
        return inscriptionDAO.ajouter(inscription);
    }

    public List<Etudiant> listerEtudiantsParFormation(int formationId) throws SQLException {
        return inscriptionDAO.listerEtudiantsParFormation(formationId);
    }
}