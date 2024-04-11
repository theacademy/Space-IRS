package org.spaceirs.dao;

import org.spaceirs.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepo extends JpaRepository<Species, Integer> {
}
