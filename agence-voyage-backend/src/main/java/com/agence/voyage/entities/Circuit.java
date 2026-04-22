package com.agence.voyage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Circuit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCircuit;

    private String nomCircuit;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "circuit")
    private List<Voyage> voyages;

    public Circuit() {
    }

    public Circuit(String nomCircuit, String description) {
        this.nomCircuit = nomCircuit;
        this.description = description;
    }

    public Circuit(Long idCircuit, String nomCircuit, String description, List<Voyage> voyages) {
        this.idCircuit = idCircuit;
        this.nomCircuit = nomCircuit;
        this.description = description;
        this.voyages = voyages;
    }

    public Long getIdCircuit() {
        return idCircuit;
    }

    public void setIdCircuit(Long idCircuit) {
        this.idCircuit = idCircuit;
    }

    public String getNomCircuit() {
        return nomCircuit;
    }

    public void setNomCircuit(String nomCircuit) {
        this.nomCircuit = nomCircuit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Voyage> getVoyages() {
        return voyages;
    }

    public void setVoyages(List<Voyage> voyages) {
        this.voyages = voyages;
    }
}
