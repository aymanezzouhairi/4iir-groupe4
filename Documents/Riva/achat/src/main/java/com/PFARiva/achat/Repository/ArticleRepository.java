package com.PFARiva.achat.Repository;

import com.PFARiva.achat.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
