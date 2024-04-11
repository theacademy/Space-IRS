package com.wiley.spaceIRS.dao;

import com.wiley.spaceIRS.entity.TaxGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxGroupRepo extends JpaRepository<TaxGroup, Integer> {
}
