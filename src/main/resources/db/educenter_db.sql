CREATE DATABASE IF NOT EXISTS educenter;
USE educenter

CREATE TABLE etudiants (
    etudiant_id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE formations (
    formation_id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(200) NOT NULL,
    duree_jours INT NOT NULL CHECK (duree_jours > 0),
    prix_fcfa DECIMAL (12,2) NOT NULL CHECK (prix_fcfa > 0) 
);

CREATE TABLE inscriptions (
    inscription_id INT AUTO_INCREMENT PRIMARY KEY,
    etudiant_id INT NOT NULL,
    formation_id INT NOT NULL,
    date_inscription DATE NOT NULL DEFAULT CURRENT_DATE,

    CONSTRAINT fk_inscription_etudiant 
        FOREIGN KEY (etudiant_id)
        REFERENCES etudiants (etudiant_id)
        ON DELETE CASCADE,

    CONSTRAINT uq_inscription_etudiant_formation
        UNIQUE (etudiant_id, formation_id)
);

INSERT INTO etudiants (nom, prenom, email) VALUES
('Diop', 'Amadou', 'amadou.diop@email.com'),
('Ndiaye', 'Fatou', 'fatou.ndiaye@email.com'),
('Ba', 'Moussa', 'moussa.ba@email.com'),
('Sow', 'Aminata', 'aminata.sow@email.com'),
('Gueye', 'Ibrahim', 'ibrahim.gueye@email.com');

INSERT INTO formations (titre, duree_jours, prix_fcfa) VALUES
('Développement Web Full-Stack', 90, 450000.00),
('Introduction à Java', 30, 150000.00),
('Bases de données SQL avancées', 15, 100000.00),
('UML et Modélisation', 10, 75000.00);

INSERT INTO inscriptions (etudiant_id, formation_id, date_inscription) VALUES
(1, 1, '2026-01-10'),
(1, 2, '2026-02-15'),
(2, 1, '2026-01-12'),
(3, 3, '2026-03-01'),
(4, 1, '2026-01-20'),
(4, 4, '2026-03-05'),
(5, 2, '2026-02-18');