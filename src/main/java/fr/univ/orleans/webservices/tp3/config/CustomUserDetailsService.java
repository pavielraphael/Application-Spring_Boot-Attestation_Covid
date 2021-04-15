package fr.univ.orleans.webservices.tp3.config;

import fr.univ.orleans.webservices.tp3.controller.ControleurRest;
import fr.univ.orleans.webservices.tp3.modele.facade.FacadeUtilisateur;
import fr.univ.orleans.webservices.tp3.modele.facade.FacadeUtilisateurImpl;
import fr.univ.orleans.webservices.tp3.modele.modele.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomUserDetailsService implements UserDetailsService {

    private static final String[] ROLES_POLICIER = {"USER","POLICIER"};
    private static final String[] ROLES_USER = {"USER"};
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Utilisateur utilisateur = ControleurRest.getFacadeUtilisateur().getUtilisateursInscrits().get(s);
        if(utilisateur == null)
        {
            throw new UsernameNotFoundException("Utilisateur " + s + " introuvable");
        }
        String[] roles = utilisateur.isPolicier() ? ROLES_POLICIER : ROLES_USER;
        UserDetails userDetails = User.builder()
                .username(utilisateur.getNom())
                .password(passwordEncoder.encode(utilisateur.getMdp()))
                .roles(roles)
                .build();
        return userDetails;
    }
}
