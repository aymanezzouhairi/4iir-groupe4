package com.PFARiva.achat.Controller;

import com.PFARiva.achat.Service.TypeArticleService;
import com.PFARiva.achat.models.TypeArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/typesArticle")
public class TypeArticleController {

    @Autowired
    private TypeArticleService typeArticleService;

    @GetMapping
    public ResponseEntity<List<TypeArticle>> getAllTypes() {
        List<TypeArticle> types = typeArticleService.getAllTypes();
        return ResponseEntity.ok(types);
    }

    @PostMapping("/add")
    public ResponseEntity<TypeArticle> addTypeArticle(@RequestBody TypeArticle typeArticle) {
        TypeArticle createdTypeArticle = typeArticleService.createTypeArticle(typeArticle);
        return ResponseEntity.status(201).body(createdTypeArticle);
    }
}

