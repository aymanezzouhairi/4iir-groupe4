package com.PFARiva.achat.Repository;

import com.PFARiva.achat.models.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeRepository extends JpaRepository<Demande,Long> {
}
