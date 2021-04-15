package fr.univ.orleans.webservices.tp3.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fr.univ.orleans.webservices.tp3.modele.facade.FacadeAttestation;
import fr.univ.orleans.webservices.tp3.modele.facade.FacadeAttestationImpl;
import fr.univ.orleans.webservices.tp3.modele.facade.FacadeUtilisateur;
import fr.univ.orleans.webservices.tp3.modele.facade.FacadeUtilisateurImpl;
import fr.univ.orleans.webservices.tp3.modele.modele.Attestation;
import fr.univ.orleans.webservices.tp3.modele.modele.Utilisateur;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

@RestController
@RequestMapping(value = "/covid", produces = MediaType.APPLICATION_JSON_VALUE)

public class ControleurRest {

    @Autowired
    private static FacadeUtilisateur facadeUtilisateur= new FacadeUtilisateurImpl();
    @Autowired
    private static FacadeAttestation facadeAttestation= new FacadeAttestationImpl();
    UUID uuid;

    public static FacadeUtilisateur getFacadeUtilisateur() {
        return facadeUtilisateur;
    }

    public static FacadeAttestation getFacadeAttestation() {
        return facadeAttestation;
    }

    @PostMapping("/utilisateur") // Protocole : POST // Port : 8080 // Path : /video // Paramètre : utilisateur(string,string)
    public ResponseEntity<String> inscription(@RequestBody Utilisateur user) {
        try {
            Predicate<String> isOk = s -> (s!=null)&&(s.length()>=2);
            if(!isOk.test(user.getNom()) || !isOk.test(user.getMdp()))
            {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Login ou/et mot de passe trop court(s)");
            }
            String nomCompte = facadeUtilisateur.inscription(user.getNom(), user.getMdp(), user.getDateNaissance(), user.getLieuNaissance(), user.isPolicier());
            System.out.println(facadeUtilisateur.getUtilisateursInscrits().size());
            return ResponseEntity.status(HttpStatus.CREATED).body(nomCompte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


    @GetMapping("/utilisateur/{login}")
    public ResponseEntity<Utilisateur> findUtilisateurByLogin(Principal principal, @PathVariable("login") String login){

        if(!principal.getName().equals(login))
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(facadeUtilisateur.getUtilisateursInscrits().containsKey(login))
        {
            return ResponseEntity.ok().body(facadeUtilisateur.getUtilisateursInscrits().get(login));
        }
        else
        {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/utilisateur/attestation/new")
    public ResponseEntity<UUID> genererAttestation(Principal principal, @RequestBody Attestation attestation){

        Attestation res = new Attestation(principal.getName(), attestation.getMotif());
        UUID id = facadeAttestation.ajouterAttestation(res);
        return ResponseEntity.ok().body(id);

    }

    @GetMapping("/utilisateur/attestation")
    public ResponseEntity<Map<UUID,Attestation>> getAllAttestations()
    {
        try
        {
            return ResponseEntity.ok().body(facadeAttestation.getAttestations());
        }
        catch (Exception e )
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/utilisateur/attestation/{uuid}")
    public ResponseEntity<Attestation> findAttestationByUUID(Principal principal, @PathVariable("uuid") String uuid) throws JsonProcessingException {
        

        UUID res = UUID.fromString(uuid);

        try{
            return ResponseEntity.ok().body(facadeAttestation.getAttestationByUUID(res));
        }
        catch(Exception e)
        {
            return ResponseEntity.notFound().build();
        }

    }
}
