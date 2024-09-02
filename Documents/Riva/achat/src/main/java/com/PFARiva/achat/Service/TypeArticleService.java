package com.PFARiva.achat.Service;

import com.PFARiva.achat.Repository.TypeArticleRepository;
import com.PFARiva.achat.models.TypeArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeArticleService {


    @Autowired
    private TypeArticleRepository typeArticleRepository;

    public List<TypeArticle> getAllTypes() {
        return typeArticleRepository.findAll();
    }

    public TypeArticle addTypeArticle(TypeArticle typeArticle) {
        return typeArticleRepository.save(typeArticle);
    }
    public Optional<TypeArticle> getTypeById(Long id) {
        return typeArticleRepository.findById(id);
    }
    public TypeArticle createTypeArticle(TypeArticle typeArticle) {
        return typeArticleRepository.save(typeArticle);
    }
}

