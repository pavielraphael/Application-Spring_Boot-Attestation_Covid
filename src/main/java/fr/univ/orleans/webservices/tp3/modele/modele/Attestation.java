package fr.univ.orleans.webservices.tp3.modele.modele;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Attestation {

    String utilisateurNom;
    LocalDateTime dateSortie;
    String motif;

    public Attestation(String utilisateurNom, String motif)
    {
        this.utilisateurNom = utilisateurNom;
        this.dateSortie = LocalDateTime.now();
        this.motif = motif;
    }

    public String getUtilisateurNom() {
        return utilisateurNom;
    }

    public LocalDateTime getDateSortie() {
        return dateSortie;
    }

    public String getMotif() {
        return motif;
    }
}
