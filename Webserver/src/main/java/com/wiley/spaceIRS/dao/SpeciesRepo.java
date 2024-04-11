package com.wiley.spaceIRS.dao;

import com.wiley.spaceIRS.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepo extends JpaRepository<Species, Integer> {
}
