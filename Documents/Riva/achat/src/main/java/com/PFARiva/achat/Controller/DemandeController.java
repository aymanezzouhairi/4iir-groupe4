package com.PFARiva.achat.Controller;

import com.PFARiva.achat.DTO.DemandeDto;
import com.PFARiva.achat.Repository.ArticleRepository;
import com.PFARiva.achat.Repository.DemandeRepository;
import com.PFARiva.achat.Service.ArticleService;
import com.PFARiva.achat.Service.DemandeService;
import com.PFARiva.achat.Service.TypeArticleService;
import com.PFARiva.achat.Service.UtilisateurService;
import com.PFARiva.achat.models.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/demandes")
public class DemandeController {

    @Autowired
    private DemandeService demandeService;



    @Autowired
    private ArticleService articleService;

     @Autowired
     private TypeArticleService typeArticleService;
    // AJOUTE UNE DEMANDE
    @PostMapping("/add")
    public ResponseEntity<Demande> createRequest(@RequestBody DemandeDto demandeDto) {
        System.out.println("Received demandeDto: " + demandeDto);
        // Vérifier si les articles demandés existent
        List<Article> articles = articleService.getAllArticles();
        List<Long> articleIds = demandeDto.getArticleIds();
        Optional<TypeArticle> typeArticleOptional = typeArticleService.getTypeById(demandeDto.getTypeArticleId());

        if (!typeArticleOptional.isPresent()) {
            return ResponseEntity.badRequest().body(null); // Type d'article n'existe pas
        }

        TypeArticle typeArticle = typeArticleOptional.get();
        boolean allArticlesExist = articleIds.stream()
                .allMatch(id -> articles.stream().anyMatch(article -> article.getId().equals(id)));

        if (!allArticlesExist) {
            return ResponseEntity.badRequest().body(null); // Certains articles n'existent pas
        }

        Demande demande = new Demande();
        demande.setTitreDemande(demandeDto.getTitreDemande());
        demande.setDescription(demandeDto.getDescription());
        demande.setMontant(demandeDto.getMontant());
        demande.setArticleIds(articleIds);
        demande.setTypeArticle(typeArticle);

        Demande newDemande = demandeService.createRequest(demande);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDemande);
    }

    // ADMIN LISTE TOUTES LES DEMANDES
    @GetMapping("/admin/list")
    public ResponseEntity<List<Demande>> getAllRequests() {
        List<Demande> allRequests = demandeService.getAllRequests();
        return ResponseEntity.ok(allRequests);
    }

    @PatchMapping("/admin/update/status/{id}")
    public ResponseEntity<?> updateRequestStatus(@PathVariable Long id, @RequestBody Map<String, String> statusMap) {
        Optional<Demande> existingDemandeOptional = demandeService.getRequestById(id);
        if (existingDemandeOptional.isPresent()) {
            Demande existingDemande = existingDemandeOptional.get();
            String newStatus = statusMap.get("status");

            if (newStatus == null || newStatus.isEmpty()) {
                return ResponseEntity.badRequest().body("Le statut est requis.");
            }

            existingDemande.setStatus(newStatus);
            Demande updatedDemande = demandeService.updateRequest(existingDemande);
            return ResponseEntity.ok(updatedDemande);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Demande non trouvée.");
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<Demande> getRequestById(@PathVariable Long id) {
        Optional<Demande> demande = demandeService.getRequestById(id);
        return demande.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }



    @GetMapping("/types")
    public List<TypeArticle> getAllTypeArticles() {
        return typeArticleService.getAllTypes();
    }

    // ADMIN SUPPRIME UNE DEMANDE
    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long id) {
        Optional<Demande> demandeOptional = demandeService.getRequestById(id);
        if (demandeOptional.isPresent()) {
            demandeService.deleteRequest(id);
            return ResponseEntity.ok("La demande avec l'ID " + id + " a été supprimée avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La demande avec l'ID " + id + " n'existe pas.");
        }
    }

}



/*
@RestController
@RequestMapping("/api/demandes")
public class DemandeController {

    @Autowired
    private DemandeService demandeService;

    @PostMapping("/create")
    public ResponseEntity<?> createDemande(@RequestBody Demande demande, HttpSession session) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé dans la session.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
        if (utilisateur.getRole() != Role.USER) {
            System.out.println("Rôle de l'utilisateur: " + utilisateur.getRole());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
        try {
            demande.setUtilisateur(utilisateur);
            Demande createdDemande = demandeService.createDemande(demande);
            return ResponseEntity.ok(createdDemande);
        } catch (Exception e) {
            e.printStackTrace();  // Ajoutez cette ligne pour plus d'infos sur l'erreur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating demande: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllDemandes(HttpSession session) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé dans la session.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
        if (utilisateur.getRole() != Role.ADMIN) {
            System.out.println("Rôle de l'utilisateur: " + utilisateur.getRole());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
        try {
            return ResponseEntity.ok(demandeService.getAllDemandes());
        } catch (Exception e) {
            e.printStackTrace();  // Ajoutez cette ligne pour plus d'infos sur l'erreur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving demandes: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/traiter")
    public ResponseEntity<?> traiterDemande(@PathVariable Long id, @RequestParam String status, HttpSession session) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé dans la session.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
        if (utilisateur.getRole() != Role.ADMIN) {
            System.out.println("Rôle de l'utilisateur: " + utilisateur.getRole());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
        try {
            Demande demande = demandeService.findById(id);
            if (demande == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Demande not found");
            }
            demande.setStatus(status);
            Demande updatedDemande = demandeService.upDateDemande(demande);
            return ResponseEntity.ok(updatedDemande);
        } catch (Exception e) {
            e.printStackTrace();  // Ajoutez cette ligne pour plus d'infos sur l'erreur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating demande: " + e.getMessage());
        }
    }
}

*/