package org.spaceirs.dao;

import org.spaceirs.entity.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SettlementRepo extends JpaRepository<Settlement, Integer> {
    @Query(value = "SELECT * FROM settlement WHERE `name` LIKE %?1%", nativeQuery = true)
    List<Settlement> searchForSettlements(String name);
}
