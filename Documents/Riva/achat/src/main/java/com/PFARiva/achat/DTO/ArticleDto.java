package com.PFARiva.achat.DTO;


public class ArticleDto {
    private String nom;
    private String description;
    private Double prix;
    private String typeArticleName;

    // Getters and Setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getTypeArticleName() {
        return typeArticleName;
    }

    public void setTypeArticleName(String typeArticleName) {
        this.typeArticleName = typeArticleName;
    }
}
