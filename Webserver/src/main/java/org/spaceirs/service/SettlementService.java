package org.spaceirs.service;

import org.spaceirs.entity.Settlement;

import java.math.BigDecimal;
import java.util.List;

public interface SettlementService {
    List<Settlement> getAllSettlements();
    Settlement getSettlementById(int id);
    Settlement addNewSettlement(Settlement settlement);
    Settlement updateSettlementData(int id, Settlement settlement);
    void deleteSettlementById(int id);
    Settlement addSpeciesToSettlement(int setId, int speId, BigDecimal population);
    Settlement addChildSettlementToSettlement(int setId);
}
