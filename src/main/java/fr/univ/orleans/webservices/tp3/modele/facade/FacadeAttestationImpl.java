package fr.univ.orleans.webservices.tp3.modele.facade;
import fr.univ.orleans.webservices.tp3.modele.modele.Attestation;
import fr.univ.orleans.webservices.tp3.modele.modele.Utilisateur;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component("facadeAttestation")
public class FacadeAttestationImpl implements FacadeAttestation{
    private static Map<UUID, Attestation> attestations;

    public FacadeAttestationImpl() {
        attestations = new HashMap<>();
    }

    public Map<UUID, Attestation> getAttestations() {
        for(Map.Entry<UUID,Attestation> entry : attestations.entrySet())
        {
            System.out.println(entry.getKey() + " : de " + entry.getValue().getUtilisateurNom() + " le " + entry.getValue().getDateSortie() + " pour " + entry.getValue().getMotif());
        }
        return attestations;
    }

    public Attestation getAttestationByUUID(UUID uuid){
        Attestation res = attestations.get(uuid);
        if (res != null)
        {
            return res;
        }
        else
        {
            return null;
        }

    }

    public UUID ajouterAttestation(Attestation attestation)
    {
        UUID newId = UUID.randomUUID();
        while(attestations.containsKey(newId))
        {
            newId=UUID.randomUUID();
        }
        attestations.put(newId,attestation);
        return newId;
    }

}
