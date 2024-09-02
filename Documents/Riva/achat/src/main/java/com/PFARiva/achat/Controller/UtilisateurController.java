package com.PFARiva.achat.Controller;

import com.PFARiva.achat.DTO.CreateUserDTO;
import com.PFARiva.achat.Service.UtilisateurService;
import com.PFARiva.achat.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody CreateUserDTO newUserDTO) {
        try {
            // Conversion DTO en Entité
            Utilisateur newUser = new Utilisateur();
            newUser.setUsername(newUserDTO.getUsername());
            newUser.setPassword(newUserDTO.getPassword());
            newUser.setRole(newUserDTO.getRole());

            // Ajout de l'utilisateur
            Utilisateur createdUser = utilisateurService.AddUtilisateur(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (IllegalArgumentException e) {
            // Gestion des erreurs de validation
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Gestion des autres erreurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la création de l'utilisateur.");
        }
    }
}
