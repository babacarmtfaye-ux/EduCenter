package com.educenter.view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.educenter.model.Etudiant;
import com.educenter.model.Formation;
import com.educenter.service.EtudiantService;
import com.educenter.service.FormationService;
import com.educenter.service.InscriptionService;

public class MenuPrincipal {

    private final Scanner scanner;
    private final EtudiantService etudiantService;
    private final FormationService formationService;
    private final InscriptionService inscriptionService;

    public MenuPrincipal() {
        this.scanner = new Scanner(System.in);
        this.etudiantService = new EtudiantService();
        this.formationService = new FormationService();
        this.inscriptionService = new InscriptionService();
    }

    public static void main(String[] args) {
        MenuPrincipal menu = new MenuPrincipal();
        menu.demarrer();
    }

    public void demarrer() {
        try (scanner) {
            boolean continuer = true;

            while (continuer) {
                afficherMenu();
                int choix = lireEntier("Votre choix : ");

                switch (choix) {
                    case 1 ->
                        ajouterEtudiant();
                    case 2 ->
                        ajouterFormation();
                    case 3 ->
                        inscrireEtudiant();
                    case 4 ->
                        afficherInscritsParFormation();
                    case 5 ->
                        calculerRevenuFormation();
                    case 6 ->
                        continuer = false;
                    default ->
                        System.out.println("Choix invalide, veuillez réessayer.");
                }
            }

            System.out.println("Fermeture de l'application. À bientôt !");
        }
    }

    private void afficherMenu() {
        System.out.println("\n===== EduCenter - Menu Principal =====");
        System.out.println("1. Ajouter un nouvel étudiant");
        System.out.println("2. Ajouter une nouvelle formation");
        System.out.println("3. Inscrire un étudiant à une formation");
        System.out.println("4. Afficher les inscrits pour une formation");
        System.out.println("5. Calculer le revenu total d'une formation");
        System.out.println("6. Quitter");
    }

    private void ajouterEtudiant() {
        System.out.println("\n--- Ajout d'un étudiant ---");
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();

        try {
            Etudiant etudiant = etudiantService.ajouterEtudiant(nom, prenom, email);
            System.out.println("Étudiant ajouté avec succès : " + etudiant);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur de saisie : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erreur base de données : " + e.getMessage());
        }
    }

    private void ajouterFormation() {
        System.out.println("\n--- Ajout d'une formation ---");
        System.out.print("Titre : ");
        String titre = scanner.nextLine();
        int duree = lireEntier("Durée (en jours) : ");
        double prix = lireDouble("Prix (en FCFA) : ");

        try {
            Formation formation = formationService.ajouterFormation(titre, duree, prix);
            System.out.println("Formation ajoutée avec succès : " + formation);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur de saisie : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erreur base de données : " + e.getMessage());
        }
    }

    private void inscrireEtudiant() {
        System.out.println("\n--- Inscription d'un étudiant ---");

        try {
            List<Etudiant> etudiants = etudiantService.listerTousLesEtudiants();
            List<Formation> formations = formationService.listerToutesLesFormations();

            System.out.println("\nListe des étudiants :");
            etudiants.forEach(System.out::println);

            System.out.println("\nListe des formations :");
            formations.forEach(System.out::println);

            int etudiantId = lireEntier("\nID de l'étudiant à inscrire : ");
            int formationId = lireEntier("ID de la formation : ");

            inscriptionService.inscrireEtudiant(etudiantId, formationId);
            System.out.println("Inscription réussie !");

        } catch (IllegalStateException e) {
            System.out.println("Inscription refusée : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erreur base de données : " + e.getMessage());
        }
    }

    private void afficherInscritsParFormation() {
        System.out.println("\n--- Inscrits pour une formation ---");
        int formationId = lireEntier("ID de la formation : ");

        try {
            List<Etudiant> etudiants = inscriptionService.listerEtudiantsParFormation(formationId);

            if (etudiants.isEmpty()) {
                System.out.println("Aucun étudiant inscrit à cette formation.");
            } else {
                System.out.println("Étudiants inscrits :");
                etudiants.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.out.println("Erreur base de données : " + e.getMessage());
        }
    }

    private void calculerRevenuFormation() {
        System.out.println("\n--- Revenu d'une formation ---");
        int formationId = lireEntier("ID de la formation : ");

        try {
            double revenu = formationService.calculerRevenu(formationId);
            System.out.printf("Revenu total généré : %.2f FCFA%n", revenu);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erreur base de données : " + e.getMessage());
        }
    }

    private int lireEntier(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Veuillez saisir un nombre entier valide.");
            System.out.print(message);
            scanner.next();
        }
        int valeur = scanner.nextInt();
        scanner.nextLine();
        return valeur;
    }

    private double lireDouble(String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            System.out.println("Veuillez saisir un nombre valide.");
            System.out.print(message);
            scanner.next();
        }
        double valeur = scanner.nextDouble();
        scanner.nextLine();
        return valeur;
    }
}
