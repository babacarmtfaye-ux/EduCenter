package com.educenter.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.educenter.model.Etudiant;
import com.educenter.model.Inscription;
import com.educenter.util.ConnexionBD;

public class InscriptionDAO {

    public boolean existeInscription(int etudiantId, int formationId) throws SQLException {
        String sql = "SELECT 1 FROM inscriptions WHERE etudiant_id = ? AND formation_id = ?";

        try (Connection connexion = ConnexionBD.getConnexion(); PreparedStatement stmt = connexion.prepareStatement(sql)) {

            stmt.setInt(1, etudiantId);
            stmt.setInt(2, formationId);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public Inscription ajouter(Inscription inscription) throws SQLException {
        String sql = "INSERT INTO inscriptions (etudiant_id, formation_id, date_inscription) VALUES (?, ?, ?)";

        try (Connection connexion = ConnexionBD.getConnexion(); PreparedStatement stmt = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, inscription.getEtudiantId());
            stmt.setInt(2, inscription.getFormationId());
            stmt.setDate(3, Date.valueOf(inscription.getDateInscription()));
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    inscription.setId(rs.getInt(1));
                }
            }
        }
        return inscription;
    }

    public List<Etudiant> listerEtudiantsParFormation(int formationId) throws SQLException {
        List<Etudiant> etudiants = new ArrayList<>();
        String sql = "SELECT e.etudiant_id, e.nom, e.prenom, e.email "
                + "FROM etudiants e "
                + "INNER JOIN inscriptions i ON i.etudiant_id = e.etudiant_id "
                + "WHERE i.formation_id = ?";

        try (Connection connexion = ConnexionBD.getConnexion(); PreparedStatement stmt = connexion.prepareStatement(sql)) {

            stmt.setInt(1, formationId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Etudiant e = new Etudiant(
                            rs.getInt("etudiant_id"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("email")
                    );
                    etudiants.add(e);
                }
            }
        }
        return etudiants;
    }
}
