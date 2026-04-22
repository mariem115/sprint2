package com.agence.voyage.dto;

import java.util.Date;

public class VoyageDTO {

    private Long idVoyage;
    private String destination;
    private Double prix;
    private Date dateDepart;
    private Date dateRetour;
    private Long idCircuit;
    private String nomCircuit;
    private String imageUrl;

    public VoyageDTO() {
    }

    public VoyageDTO(Long idVoyage, String destination, Double prix, Date dateDepart,
                     Date dateRetour, Long idCircuit, String nomCircuit, String imageUrl) {
        this.idVoyage = idVoyage;
        this.destination = destination;
        this.prix = prix;
        this.dateDepart = dateDepart;
        this.dateRetour = dateRetour;
        this.idCircuit = idCircuit;
        this.nomCircuit = nomCircuit;
        this.imageUrl = imageUrl;
    }

    public Long getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(Long idVoyage) {
        this.idVoyage = idVoyage;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
