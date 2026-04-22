package com.agence.voyage.service;

import com.agence.voyage.entities.Circuit;

import java.util.List;

public interface CircuitService {

    Circuit saveCircuit(Circuit c);

    Circuit updateCircuit(Circuit c);

    void deleteCircuit(Circuit c);

    void deleteCircuitById(Long id);

    Circuit getCircuit(Long id);

    List<Circuit> getAllCircuits();
}
