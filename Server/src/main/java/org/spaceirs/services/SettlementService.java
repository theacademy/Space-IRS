package org.spaceirs.services;

import org.spaceirs.entity.Settlement;

import java.math.BigDecimal;
import java.util.List;

public interface SettlementService {
    List<Settlement> getAllSettlements();

    Settlement getSettlementById(int id) throws ServicePersistenceException;

    Settlement addNewSettlement(Settlement settlement) throws ServicePersistenceException;

    Settlement updateSettlementData(int id, Settlement settlement) throws ServicePersistenceException;

    void deleteSettlementById(int id) throws ServicePersistenceException;

    List<Settlement> searchForSettlement(String name);

    BigDecimal getInhabitantTax(int speciesId, int settlementId);
}
