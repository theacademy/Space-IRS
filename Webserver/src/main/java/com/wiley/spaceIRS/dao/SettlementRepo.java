package com.wiley.spaceIRS.dao;


import com.wiley.spaceIRS.entity.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettlementRepo extends JpaRepository<Settlement, Integer> {
}
