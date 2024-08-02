package com.stage.projetDemande.Conrolleur;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
public class datapowerbi {
    @GetMapping("/report")
    public ResponseEntity<List<DiagramData>> getReportData() {
        // Logique pour lire le fichier CSV/Excel et retourner les données
        return ResponseEntity.ok(diagramDataList);
}
