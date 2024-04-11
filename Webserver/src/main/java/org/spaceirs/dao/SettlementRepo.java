package org.spaceirs.dao;

import org.spaceirs.entity.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettlementRepo extends JpaRepository<Settlement, Integer> {
}
