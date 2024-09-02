package com.PFARiva.achat.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@Entity
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String titre_demande;
    private Double montant;
    private String status;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre_demande() {
        return titre_demande;
    }

    public void setTitre_demande(String titre_demande) {
        this.titre_demande = titre_demande;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTitreDemande(String titreDemande) {
    }

    public void setArticleIds(List<Long> articleIds) {
    }

    public void setTypes(List<TypeArticle> types) {
    }

    public void setTypeArticle(TypeArticle typeArticle) {
    }
}
