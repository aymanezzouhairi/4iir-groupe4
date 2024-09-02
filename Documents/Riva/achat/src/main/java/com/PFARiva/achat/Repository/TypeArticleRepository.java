package com.PFARiva.achat.Repository;

import com.PFARiva.achat.models.TypeArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeArticleRepository extends JpaRepository<TypeArticle,Long> {
    @Override
    Optional<TypeArticle> findById(Long id);
}
