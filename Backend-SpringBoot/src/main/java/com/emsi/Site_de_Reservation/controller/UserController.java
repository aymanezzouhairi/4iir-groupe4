package com.emsi.Site_de_Reservation.controller;

import com.emsi.Site_de_Reservation.model.User;
import com.emsi.Site_de_Reservation.service.UserService;
import com.emsi.Site_de_Reservation.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:/3000")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final JwtUtil jwtUtil;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(HttpServletRequest request,
                                                 @RequestParam("first_name") String first_name,
                                                 @RequestParam("last_name") String last_name,
                                                 @RequestParam("username") String username,
                                                 @RequestParam("email") String email,
                                                 @RequestParam("password") String password,
                                                 @RequestParam("user_avatar") MultipartFile user_avatar_file,
                                                 @RequestParam("user_cover") MultipartFile user_cover_file,
                                                 @RequestParam(value = "isAdmin", defaultValue = "false") boolean isAdmin) throws IOException, SQLException {

        // Vérifier si le nom d'utilisateur existe déjà
        if (userService.existsByUsername(username)) {
            return ResponseEntity.badRequest().body("Username déjà utilisé !");
        }

        // Vérifier si l'e-mail existe déjà
        if (userService.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("E-mail déjà utilisé !");
        }
           // Convertion du user avatar en bytes
           byte[] avatar_bytes = user_avatar_file.getBytes();
           Blob avatar_blob = new javax.sql.rowset.serial.SerialBlob(avatar_bytes);

            // Convertion du user cover en bytes
            byte[] cover_bytes = user_cover_file.getBytes();
            Blob cover_blob = new javax.sql.rowset.serial.SerialBlob(cover_bytes);

            User user = new User();
            user.setFirst_name(first_name);
            user.setLast_name(last_name);
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setUser_avatar(avatar_blob);
            user.setUser_cover(cover_blob);
            user.setAdmin(isAdmin);

            userService.createUser(user);
            return ResponseEntity.ok("Compte créer avec succées :)");
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<?> loginUser(@RequestBody User user, HttpServletResponse response) {
        String username = user.getUsername();
        String password = user.getPassword();

        try {
            Optional<User> foundUser = userService.findByUsername(username);

            if (foundUser.isPresent()) {
                // Récupérer le mot de passe hashé de l'utilisateur trouvé
                String hashedPassword = foundUser.get().getPassword();

                // Vérifier si le mot de passe entré correspond au mot de passe hashé
                if (passwordEncoder.matches(password, hashedPassword)) {
                    // Authentification réussie
                    String token = jwtUtil.generateToken(user.getUsername());
                    System.out.println("Token généré : " + token);

                    // Récupérer l'utilisateur avec toutes ses informations
                    User loggedInUser = foundUser.get();

                    return ResponseEntity.ok(loggedInUser);
                } else {
                    // Mot de passe incorrect
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants incorrects");
                }
            } else {
                // Utilisateur non trouvé
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants incorrects");
            }
        } catch (Exception e) {
            // Gérer toute exception interne
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur");
        }
    }
}