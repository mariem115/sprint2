package com.agence.voyage.restcontrollers;

import com.agence.voyage.entities.Circuit;
import com.agence.voyage.service.CircuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CircuitRESTController {

    @Autowired
    private CircuitService circuitService;

    @GetMapping("/circuits")
    public List<Circuit> getAllCircuits() {
        return circuitService.getAllCircuits();
    }

    @GetMapping("/circuits/{id}")
    public Circuit getCircuitById(@PathVariable Long id) {
        return circuitService.getCircuit(id);
    }

    @PostMapping("/circuits")
    public Circuit createCircuit(@RequestBody Circuit circuit) {
        return circuitService.saveCircuit(circuit);
    }

    @PutMapping("/circuits/{id}")
    public Circuit updateCircuit(@PathVariable Long id, @RequestBody Circuit circuit) {
        circuit.setIdCircuit(id);
        return circuitService.updateCircuit(circuit);
    }

    @DeleteMapping("/circuits/{id}")
    public void deleteCircuit(@PathVariable Long id) {
        circuitService.deleteCircuitById(id);
    }
}
