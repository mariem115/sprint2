package com.agence.voyage.repos;

import com.agence.voyage.entities.Circuit;
import com.agence.voyage.entities.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CircuitRepository extends JpaRepository<Circuit, Long> {

    List<Voyage> findByDestination(String destination);

    @Query("SELECT v FROM Voyage v WHERE v.prix < :maxPrix")
    List<Voyage> findVoyagesBelowPrice(@Param("maxPrix") Double maxPrix);

    List<Voyage> findByCircuit_IdCircuit(Long idCircuit);
}
