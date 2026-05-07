package com.agence.voyage.restcontrollers;

import com.agence.voyage.dto.VoyageDTO;
import com.agence.voyage.entities.Circuit;
import com.agence.voyage.entities.Voyage;
import com.agence.voyage.mapper.VoyageMapper;
import com.agence.voyage.service.CircuitService;
import com.agence.voyage.service.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class VoyageRESTController {

    @Autowired
    private VoyageService voyageService;

    @Autowired
    private CircuitService circuitService;

    @GetMapping("/voyages")
    public List<VoyageDTO> getAllVoyages() {
        return voyageService.getAllVoyages().stream()
                .map(VoyageMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/voyages/{id}")
    public VoyageDTO getVoyageById(@PathVariable Long id) {
        return VoyageMapper.toDTO(voyageService.getVoyage(id));
    }

    @PostMapping("/voyages")
    public VoyageDTO createVoyage(@RequestBody VoyageDTO voyageDTO) {
        Circuit circuit = voyageDTO.getIdCircuit() != null
                ? circuitService.getCircuit(voyageDTO.getIdCircuit())
                : null;
        Voyage voyage = VoyageMapper.toEntity(voyageDTO, circuit);
        Voyage saved = voyageService.saveVoyage(voyage);
        return VoyageMapper.toDTO(saved);
    }

    @PutMapping("/voyages/{id}")
    public VoyageDTO updateVoyage(@PathVariable Long id, @RequestBody VoyageDTO voyageDTO) {
        voyageDTO.setIdVoyage(id);
        Circuit circuit = voyageDTO.getIdCircuit() != null
                ? circuitService.getCircuit(voyageDTO.getIdCircuit())
                : null;
        Voyage voyage = VoyageMapper.toEntity(voyageDTO, circuit);
        Voyage updated = voyageService.updateVoyage(voyage);
        return VoyageMapper.toDTO(updated);
    }

    @DeleteMapping("/voyages/{id}")
    public void deleteVoyage(@PathVariable Long id) {
        voyageService.deleteVoyageById(id);
    }

    @GetMapping("/voyages/byDestination")
    public List<VoyageDTO> findByDestination(@RequestParam String destination) {
        return voyageService.findByDestination(destination).stream()
                .map(VoyageMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/voyages/byCircuit/{idCircuit}")
    public List<VoyageDTO> findByCircuit(@PathVariable Long idCircuit) {
        return voyageService.findByCircuitId(idCircuit).stream()
                .map(VoyageMapper::toDTO)
                .collect(Collectors.toList());
    }
}
