package com.PFARiva.achat.Service;

import com.PFARiva.achat.Repository.ArticleRepository;
import com.PFARiva.achat.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article updateArticle(Long id, Article updatedArticle) {
        if (articleRepository.existsById(id)) {
            updatedArticle.setId(id);
            return articleRepository.save(updatedArticle);
        } else {
            throw new ResourceNotFoundException("Article not found with ID: " + id);
        }
    }

    public void deleteArticle(Long id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Article not found with ID: " + id);
        }
    }
}

