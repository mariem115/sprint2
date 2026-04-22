package com.agence.voyage.repos;

import com.agence.voyage.entities.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoyageRepository extends JpaRepository<Voyage, Long> {

    List<Voyage> findByDestination(String destination);

    List<Voyage> findByCircuit_IdCircuit(Long idCircuit);

    List<Voyage> findByOrderByPrixAsc();

    @Query("SELECT v FROM Voyage v WHERE v.prix BETWEEN :min AND :max")
    List<Voyage> findVoyagesByPriceRange(@Param("min") Double min, @Param("max") Double max);
}
