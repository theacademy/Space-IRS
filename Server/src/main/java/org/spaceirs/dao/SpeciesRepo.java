package org.spaceirs.dao;

import org.spaceirs.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesRepo extends JpaRepository<Species, Integer> {
    @Query(value = "SELECT * FROM species WHERE `name` LIKE %?1%", nativeQuery = true)
    List<Species> searchForSpecies(String name);
}
