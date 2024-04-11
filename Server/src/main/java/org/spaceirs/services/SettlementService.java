package org.spaceirs.services;

import org.spaceirs.entity.Settlement;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface SettlementService {
    List<Settlement> getAllSettlements();
    Settlement getSettlementById(int id) throws ServicePersistenceException;
    Settlement addNewSettlement(Settlement settlement) throws ServicePersistenceException;
    Settlement updateSettlementData(int id, Settlement settlement) throws ServicePersistenceException;
    void deleteSettlementById(int id) throws ServicePersistenceException;
    Settlement addSpeciesToSettlement(int setId, int speId, BigDecimal population);
    Settlement addChildSettlementToSettlement(int setId);

    List<Settlement> searchForSettlement(String name);
}