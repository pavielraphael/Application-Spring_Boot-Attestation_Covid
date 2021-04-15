package fr.univ.orleans.webservices.tp3.modele.facade;
import fr.univ.orleans.webservices.tp3.modele.modele.Utilisateur;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component("facadeUtilisateur")
public class FacadeUtilisateurImpl implements FacadeUtilisateur{
    private static Map<String, Utilisateur> utilisateursInscrits;

    public Map<String, Utilisateur> getUtilisateursInscrits() {
        return utilisateursInscrits;
    }


    public FacadeUtilisateurImpl() {
        utilisateursInscrits = new HashMap<>();
        Utilisateur fred = new Utilisateur("fred","fred","15/02/1955","Orleans",true);
        utilisateursInscrits.put(fred.getNom(),fred);
        Utilisateur raph = new Utilisateur("raph","raph","29/08/1997","Le Port",false);
        utilisateursInscrits.put(raph.getNom(),raph);
    }


    @Override
    public String inscription(String nom, String mdp, String dateNaissance, String lieuNaissance, boolean policier) throws Exception {
        if (!utilisateursInscrits.containsKey(nom)) {
            Utilisateur utilisateur = new Utilisateur(nom, mdp, dateNaissance, lieuNaissance, policier);
            utilisateursInscrits.put(nom,utilisateur);

            return nom;
        }
        throw new Exception("Utilisateur déjà inscrit");
    }
}

