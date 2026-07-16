package com.educenter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.educenter.model.Formation;
import com.educenter.util.ConnexionBD;

public class FormationDAO {

    public Formation ajouter(Formation formation) throws SQLException {
        String sql = "INSERT INTO formations (titre, duree_jours, prix_fcfa) VALUES (?, ?, ?)";

        try (Connection connexion = ConnexionBD.getConnexion(); PreparedStatement stmt = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, formation.getTitre());
            stmt.setInt(2, formation.getDureeJours());
            stmt.setDouble(3, formation.getPrixFcfa());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    formation.setId(rs.getInt(1));
                }
            }
        }
        return formation;
    }

    public List<Formation> listerToutes() throws SQLException {
        List<Formation> formations = new ArrayList<>();
        String sql = "SELECT * FROM formations";

        try (Connection connexion = ConnexionBD.getConnexion(); PreparedStatement stmt = connexion.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Formation f = new Formation(
                        rs.getInt("formation_id"),
                        rs.getString("titre"),
                        rs.getInt("duree_jours"),
                        rs.getDouble("prix_fcfa")
                );
                formations.add(f);
            }
        }
        return formations;
    }

    public Formation trouverParId(int formationId) throws SQLException {
        String sql = "SELECT * FROM formations WHERE formation_id = ?";

        try (Connection connexion = ConnexionBD.getConnexion(); PreparedStatement stmt = connexion.prepareStatement(sql)) {

            stmt.setInt(1, formationId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Formation(
                            rs.getInt("formation_id"),
                            rs.getString("titre"),
                            rs.getInt("duree_jours"),
                            rs.getDouble("prix_fcfa")
                    );
                }
            }
        }
        return null;
    }

    public int compterInscrits(int formationId) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM inscriptions WHERE formation_id = ?";

        try (Connection connexion = ConnexionBD.getConnexion(); PreparedStatement stmt = connexion.prepareStatement(sql)) {

            stmt.setInt(1, formationId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        }
        return 0;
    }
}
