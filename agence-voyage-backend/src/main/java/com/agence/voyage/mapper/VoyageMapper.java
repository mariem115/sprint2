package com.agence.voyage.mapper;

import com.agence.voyage.dto.VoyageDTO;
import com.agence.voyage.entities.Circuit;
import com.agence.voyage.entities.Voyage;

public class VoyageMapper {

    public static VoyageDTO toDTO(Voyage voyage) {
        if (voyage == null) {
            return null;
        }
        VoyageDTO dto = new VoyageDTO();
        dto.setIdVoyage(voyage.getIdVoyage());
        dto.setDestination(voyage.getDestination());
        dto.setPrix(voyage.getPrix());
        dto.setDateDepart(voyage.getDateDepart());
        dto.setDateRetour(voyage.getDateRetour());
        dto.setImageUrl(voyage.getImageUrl());
        if (voyage.getCircuit() != null) {
            dto.setIdCircuit(voyage.getCircuit().getIdCircuit());
            dto.setNomCircuit(voyage.getCircuit().getNomCircuit());
        }
        return dto;
    }

    public static Voyage toEntity(VoyageDTO dto, Circuit circuit) {
        if (dto == null) {
            return null;
        }
        Voyage voyage = new Voyage();
        voyage.setIdVoyage(dto.getIdVoyage());
        voyage.setDestination(dto.getDestination());
        voyage.setPrix(dto.getPrix());
        voyage.setDateDepart(dto.getDateDepart());
        voyage.setDateRetour(dto.getDateRetour());
        voyage.setImageUrl(dto.getImageUrl());
        voyage.setCircuit(circuit);
        return voyage;
    }
}
