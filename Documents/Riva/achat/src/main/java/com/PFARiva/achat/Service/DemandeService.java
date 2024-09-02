package com.PFARiva.achat.Service;

import com.PFARiva.achat.Repository.DemandeRepository;
import com.PFARiva.achat.Repository.TypeArticleRepository;
import com.PFARiva.achat.models.Demande;
import com.PFARiva.achat.models.TypeArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private TypeArticleRepository typeArticleRepository;

    public Demande createRequest(Demande demande) {
        if (demande.getStatus() == null) {
            demande.setStatus("En_cours_de_traitement");
        }


        return demandeRepository.save(demande);
     
    }

    public List<Demande> getAllRequests() {
        return demandeRepository.findAll();
    }

    public Optional<Demande> getRequestById(Long id) {
        return demandeRepository.findById(id);
    }

    public Demande updateRequest(Demande demande) {
        if (demande.getId() == null || !demandeRepository.existsById(demande.getId())) {
            throw new IllegalArgumentException("Demande non trouvée.");
        }
        return demandeRepository.save(demande);
    }

    public void deleteRequest(Long id) {
        demandeRepository.deleteById(id);
    }
}
