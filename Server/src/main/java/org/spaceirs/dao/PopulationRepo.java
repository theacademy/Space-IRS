package org.spaceirs.dao;

import java.util.List;

import org.spaceirs.entity.Population;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PopulationRepo extends JpaRepository<Population, Integer> {
    @Query(value = "SELECT * FROM populations WHERE populations_species_id = ?1", nativeQuery = true)
    List<Population> getSpeciesHabitats(int id);

    @Query(value = "SELECT * FROM populations WHERE populations_settlement_id = ?1", nativeQuery = true)
    List<Population> getSettlementsInhabitants(int id);

    @Query(value = "SELECT * FROM populations WHERE populations_species_id = ?1 AND populations_settlement_id = ?2", nativeQuery = true)
    Population getPopulation(int settlementId, int speciesId);
}
