package fr.univ.orleans.webservices.tp3.modele.modele;

import java.time.LocalDateTime;
import java.util.UUID;

public class Utilisateur {

    private String nom;
    private String mdp;
    private String dateNaissance;
    private String lieuNaissance;
    private boolean policier;

    private LocalDateTime dateInscription;

    public LocalDateTime getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDateTime dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Utilisateur() {
    }

    public Utilisateur( String nom, String mdp, String dateNaissance, String lieuNaissance, boolean policier) {

        this.nom = nom;
        this.mdp = mdp;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.dateInscription = LocalDateTime.now();
        this.policier = policier;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public boolean isPolicier() {
        return policier;
    }
}
