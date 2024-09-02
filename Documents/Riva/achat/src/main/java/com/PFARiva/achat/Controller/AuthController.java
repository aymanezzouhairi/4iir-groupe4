package com.PFARiva.achat.Controller;

import com.PFARiva.achat.Repository.DemandeRepository;
import com.PFARiva.achat.Repository.UtilisateurRepository;
import com.PFARiva.achat.Service.UtilisateurService;
import com.PFARiva.achat.DTO.LoginRequest;
import com.PFARiva.achat.models.Demande;
import com.PFARiva.achat.models.Utilisateur;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;




    @RestController
    @RequestMapping("/auth")
    public class AuthController {
        @Autowired
        private UtilisateurService utilisateurService;
        @Autowired
        private DemandeRepository demandeRepository;

        @PostMapping("/login")
        public ResponseEntity<String> login(@RequestBody Utilisateur user) {
            Utilisateur utilisateur = utilisateurService.authenticateUser(user.getUsername(), user.getPassword());
            if (utilisateur != null) {
                // Gérer la session ou le token ici
                return ResponseEntity.ok("Authentification réussie");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants incorrects");
            }
        }


        // Exemple de vérification des rôles dans un endpoint
        @GetMapping("/admin/list")
        public ResponseEntity<List<Demande>> getAllRequests(HttpSession session) {
            String role = (String) session.getAttribute("role");
            if ("admin".equals(role)) {
                List<Demande> allRequests = demandeRepository.findAll();
                return ResponseEntity.ok(allRequests);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        }


        @PostMapping("/logout")
        public ResponseEntity<?> logout(HttpSession session) {
            session.invalidate();
            return ResponseEntity.ok("Logged out successfully");
        }

    }
