package org.spaceirs.service;

import org.spaceirs.entity.Settlement;

import java.math.BigDecimal;
import java.util.List;

public interface SettlementService {
    List<Settlement> getAllSettlements();
    Settlement getSettlementById(int id) throws ServicePersistenceException;
    Settlement addNewSettlement(Settlement settlement) throws ServicePersistenceException;
    Settlement updateSettlementData(int id, Settlement settlement) throws ServicePersistenceException;
    void deleteSettlementById(int id) throws ServicePersistenceException;
    Settlement addSpeciesToSettlement(int setId, int speId, BigDecimal population);
    Settlement addChildSettlementToSettlement(int setId);
}
