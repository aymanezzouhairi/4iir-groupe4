package com.PFARiva.achat.DTO;

import java.util.List;

public class DemandeDto {
    private String titreDemande;
    private String description;
    private Double montant;
    private List<Long> articleIds;
    private Long typeArticleId;

    public String getTitreDemande() {
        return titreDemande;
    }

    public void setTitreDemande(String titreDemande) {
        this.titreDemande = titreDemande;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public List<Long> getArticleIds() {
        return articleIds;
    }

    public void setArticleIds(List<Long> articleIds) {
        this.articleIds = articleIds;
    }

    public Long getTypeArticleId() {
        return typeArticleId;
    }

    public void setTypeArticleId(Long typeArticleId) {
        this.typeArticleId = typeArticleId;
    }
}