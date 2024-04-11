package com.wiley.spaceIRS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SettlementServiceImpl implements SettlementService {
    @Autowired
    SettlementDao settlementDao;

    public SettlementServiceImpl(SettlementDao settlementDao){
        this.settlementDao = settlementDao;
    }

    public List<Settlement> getAllSettlements(){
        return settlementDao.getAllSettlements();
    }

    @Override
    public Settlement getSettlementById(int id) {
        Settlement settlement;
        try{
            settlement = settlementDao.findById(id);
        } catch (DataAccessException ex){
            // TODO create an exception with a correct message
        }
        return null;
    }

    @Override
    public Settlement addNewSettlement(Settlement settlement) {
        return null;
    }

    @Override
    public Settlement updateSettlementData(int id, Settlement settlement) {
        return null;
    }

    @Override
    public void deleteSettlementById(int id) {

    }

    @Override
    public Settlement addSpeciesToSettlement(int setId, int speId, BigDecimal population) {
        return null;
    }

    @Override
    public Settlement addChildSettlementToSettlement(int setId) {
        return null;
    }

}
