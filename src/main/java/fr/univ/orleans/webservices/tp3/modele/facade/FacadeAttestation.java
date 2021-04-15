package fr.univ.orleans.webservices.tp3.modele.facade;


import fr.univ.orleans.webservices.tp3.modele.modele.Attestation;
import fr.univ.orleans.webservices.tp3.modele.modele.Utilisateur;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public interface FacadeAttestation {

    public Attestation getAttestationByUUID(UUID uuid);
    public Map<UUID, Attestation> getAttestations();
    public UUID ajouterAttestation(Attestation attestation);

}
