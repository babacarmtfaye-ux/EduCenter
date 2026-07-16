package com.educenter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.educenter.model.Etudiant;
import com.educenter.util.ConnexionBD;

public class EtudiantDAO {

    public Etudiant ajouter(Etudiant etudiant) throws SQLException {
        String sql = "INSERT INTO etudiants (nom, prenom, email) VALUES (?, ?, ?)";

        try (Connection connexion = ConnexionBD.getConnexion(); PreparedStatement stmt = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, etudiant.getNom());
            stmt.setString(2, etudiant.getPrenom());
            stmt.setString(3, etudiant.getEmail());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    etudiant.setId(rs.getInt(1));
                }
            }
        }
        return etudiant;
    }

    public List<Etudiant> listerTous() throws SQLException {
        List<Etudiant> etudiants = new ArrayList<>();
        String sql = "SELECT * FROM etudiants";

        try (Connection connexion = ConnexionBD.getConnexion(); PreparedStatement stmt = connexion.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

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
        return etudiants;
    }
}
