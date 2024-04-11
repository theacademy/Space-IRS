package com.wiley.spaceIRS.service;

public interface SettlementService {
    List<Settlement> getAllSettlements();
    Settlement getSettlementById(int id);
    Settlement addNewSettlement(Settlement settlement);
    Settlement updateSettlementData(int id, Settlement settlement);
    void deleteSettlementById(int id);
}
