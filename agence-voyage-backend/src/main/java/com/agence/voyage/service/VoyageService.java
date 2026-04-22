package com.agence.voyage.service;
import com.agence.voyage.entities.Voyage;
import java.util.List;
public interface VoyageService {
    Voyage saveVoyage(Voyage v);
    Voyage updateVoyage(Voyage v);
    void deleteVoyage(Voyage v);
    void deleteVoyageById(Long id);
    Voyage getVoyage(Long id);
    List<Voyage> getAllVoyages();
    List<Voyage> findByDestination(String destination);
    List<Voyage> findByCircuitId(Long idCircuit);
    List<Voyage> getAllVoyagesSortedByPrice();
}
