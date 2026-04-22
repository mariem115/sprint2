package com.agence.voyage.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Voyage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVoyage;

    private String destination;
    private Double prix;
    private Date dateDepart;
    private Date dateRetour;
    private String imageUrl;

    @ManyToOne
    private Circuit circuit;

    public Voyage() {
    }

    public Voyage(String destination, Double prix, Date dateDepart, Date dateRetour) {
        this.destination = destination;
        this.prix = prix;
        this.dateDepart = dateDepart;
        this.dateRetour = dateRetour;
    }

    public Voyage(Long idVoyage, String destination, Double prix, Date dateDepart, Date dateRetour) {
        this.idVoyage = idVoyage;
        this.destination = destination;
        this.prix = prix;
        this.dateDepart = dateDepart;
        this.dateRetour = dateRetour;
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

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Voyage{" +
                "idVoyage=" + idVoyage +
                ", destination='" + destination + '\'' +
                ", prix=" + prix +
                ", dateDepart=" + dateDepart +
                ", dateRetour=" + dateRetour +
                '}';
    }
}
