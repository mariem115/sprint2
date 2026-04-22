package com.agence.voyage.service;

import com.agence.voyage.entities.Voyage;
import com.agence.voyage.repos.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoyageServiceImpl implements VoyageService {

    @Autowired
    private VoyageRepository voyageRepository;

    @Override
    public Voyage saveVoyage(Voyage v) {
        return voyageRepository.save(v);
    }

    @Override
    public Voyage updateVoyage(Voyage v) {
        return voyageRepository.save(v);
    }

    @Override
    public void deleteVoyage(Voyage v) {
        voyageRepository.delete(v);
    }

    @Override
    public void deleteVoyageById(Long id) {
        voyageRepository.deleteById(id);
    }

    @Override
    public Voyage getVoyage(Long id) {
        return voyageRepository.findById(id).orElse(null);
    }

    @Override
    public List<Voyage> getAllVoyages() {
        return voyageRepository.findAll();
    }

    @Override
    public List<Voyage> findByDestination(String destination) {
        return voyageRepository.findByDestination(destination);
    }

    @Override
    public List<Voyage> findByCircuitId(Long idCircuit) {
        return voyageRepository.findByCircuit_IdCircuit(idCircuit);
    }

    @Override
    public List<Voyage> getAllVoyagesSortedByPrice() {
        return voyageRepository.findByOrderByPrixAsc();
    }
}
