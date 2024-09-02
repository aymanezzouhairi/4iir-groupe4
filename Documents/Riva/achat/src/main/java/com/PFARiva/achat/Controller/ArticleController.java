package com.PFARiva.achat.Controller;

import com.PFARiva.achat.DTO.ArticleDto;
import com.PFARiva.achat.Service.ArticleService;
import com.PFARiva.achat.Service.TypeArticleService;
import com.PFARiva.achat.models.Article;
import com.PFARiva.achat.models.TypeArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TypeArticleService typeArticleService;

    @GetMapping("/all")
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }
    @GetMapping("/types")
    public ResponseEntity<List<TypeArticle>> getAllTypes() {
        List<TypeArticle> types = typeArticleService.getAllTypes();
        return ResponseEntity.ok(types);
    }
    @PostMapping("/add")
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        Article createdArticle = articleService.createArticle(article);
        return ResponseEntity.status(201).body(createdArticle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle) {
        Article article = articleService.updateArticle(id, updatedArticle);
        if (article != null) {
            return ResponseEntity.ok(article);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}


/*
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TypeArticleService typeArticleService;

    // Récupérer tous les articles
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }

    // Récupérer tous les types d'articles
    @GetMapping("/types")
    @Secured()
    public ResponseEntity<List<TypeArticle>> getAllTypes() {
        List<TypeArticle> types = typeArticleService.getAllTypes();
        return ResponseEntity.ok(types);
    }

    // Ajouter un article
    @PostMapping("/add")
    public ResponseEntity<Article> addArticle(@RequestBody ArticleDto articleDto) {
        TypeArticle typeArticle = typeArticleService.getAllTypes().stream()
                .filter(type -> type.getNom().equals(articleDto.getTypeArticleName()))
                .findFirst()
                .orElse(null);

        if (typeArticle == null) {
            return ResponseEntity.badRequest().body(null); // Type d'article non trouvé
        }

        Article article = new Article();
        article.setNom(articleDto.getNom());
        article.setDescription(articleDto.getDescription());
        article.setPrix(articleDto.getPrix());
        article.setTypeArticle(typeArticle);

        Article createdArticle = articleService.createArticle(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArticle);
    }

    // Ajouter un nouveau type d'article
    @PostMapping("/addType")
    public ResponseEntity<TypeArticle> addTypeArticle(@RequestBody TypeArticle typeArticle) {
        TypeArticle nouveauType = typeArticleService.addTypeArticle(typeArticle);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveauType);
    }

    // Mettre à jour un article
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle) {
        Article article = articleService.updateArticle(id, updatedArticle);
        return ResponseEntity.ok(article);
    }

    // Supprimer un article
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}

*/