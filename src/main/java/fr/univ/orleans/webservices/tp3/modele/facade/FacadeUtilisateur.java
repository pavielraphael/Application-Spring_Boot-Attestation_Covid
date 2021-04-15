package fr.univ.orleans.webservices.tp3.modele.facade;
import fr.univ.orleans.webservices.tp3.modele.modele.Utilisateur;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public interface FacadeUtilisateur {

    String inscription(String nom, String mdp, String dateNaissance, String lieuNaissance, boolean policier) throws Exception;

    public Map<String, Utilisateur> getUtilisateursInscrits();

}
