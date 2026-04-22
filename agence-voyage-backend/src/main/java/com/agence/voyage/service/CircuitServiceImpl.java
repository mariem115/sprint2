package com.agence.voyage.service;

import com.agence.voyage.entities.Circuit;
import com.agence.voyage.repos.CircuitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CircuitServiceImpl implements CircuitService {

    @Autowired
    private CircuitRepository circuitRepository;

    @Override
    public Circuit saveCircuit(Circuit c) {
        return circuitRepository.save(c);
    }

    @Override
    public Circuit updateCircuit(Circuit c) {
        return circuitRepository.save(c);
    }

    @Override
    public void deleteCircuit(Circuit c) {
        circuitRepository.delete(c);
    }

    @Override
    public void deleteCircuitById(Long id) {
        circuitRepository.deleteById(id);
    }

    @Override
    public Circuit getCircuit(Long id) {
        return circuitRepository.findById(id).orElse(null);
    }

    @Override
    public List<Circuit> getAllCircuits() {
        return circuitRepository.findAll();
    }
}
