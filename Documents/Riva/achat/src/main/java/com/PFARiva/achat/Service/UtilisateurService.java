package com.PFARiva.achat.Service;

import com.PFARiva.achat.Repository.UtilisateurRepository;
import com.PFARiva.achat.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur findByUsernameAndPassword(String username , String passwor){

        return utilisateurRepository.findByUsernameAndPassword(username, passwor);
    }
    public Utilisateur AddUtilisateur(Utilisateur utilisateur){
        if (!"admin".equalsIgnoreCase(utilisateur.getRole()) && !"user".equalsIgnoreCase(utilisateur.getRole())) {
            throw new IllegalArgumentException("Le rôle est invalide. Il doit être 'admin' ou 'user'.");
        }

        return utilisateurRepository.save(utilisateur);
    }
    public Utilisateur authenticateUser(String username, String password) {
        return utilisateurRepository.findByUsernameAndPassword(username, password);
    }
    public void DeleteUtilisateur(Long id){
         utilisateurRepository.deleteById(id);
    }

}
