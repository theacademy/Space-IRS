package org.spaceirs.services;

import org.spaceirs.dao.SettlementRepo;
import org.spaceirs.dao.SpeciesRepo;
import org.spaceirs.entity.Settlement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class SettlementServiceImpl implements SettlementService {
    @Autowired
    SettlementRepo settlementDao;

    @Autowired
    SpeciesRepo speciesDao;

    public SettlementServiceImpl(SettlementRepo settlementDao, SpeciesRepo speciesDao){
        this.settlementDao = settlementDao;
        this.speciesDao = speciesDao;
    }

    public List<Settlement> getAllSettlements(){
        return settlementDao.findAll();
    }

    @Override
    public Settlement getSettlementById(int id) throws ServicePersistenceException {
        Settlement settlement;
        try{
            settlement = settlementDao.findById(id).orElse(null);
            return settlement;
        } catch (DataAccessException ex){
            throw new ServicePersistenceException("Settlement not found", ex.getCause());
        }
    }

    @Override
    public Settlement addNewSettlement(Settlement settlement) throws ServicePersistenceException {
        if((settlement.getName().isEmpty() || settlement.getName().equals(""))
            || (settlement.getDirections().isEmpty() || settlement.getDirections().equals(""))
            || (settlement.getType().isEmpty() || settlement.getType().equals(""))
            || (settlement.getTaxModifier() == null || settlement.getTaxModifier().equals(""))) {

            throw new ServicePersistenceException("Invalid input, a filed is missing from settlement,");

        } else {
            settlement = settlementDao.save(settlement);
        }
        return settlement;
    }

    @Override
    public Settlement updateSettlementData(int id, Settlement settlement) throws ServicePersistenceException{
        if(settlement.getId() != id){
            throw new ServicePersistenceException("Cannot update settlement, invalid settlement id given");
        } else {
            return settlementDao.save(settlement);
        }

    }

    @Override
    public void deleteSettlementById(int id) throws ServicePersistenceException {
        try {
            Settlement settlement = settlementDao.findById(id).orElse(null);
            settlementDao.delete(settlement);
        } catch (DataAccessException ex) {
            throw new ServicePersistenceException("Settlement not found", ex.getCause());
        }
    }

    @Override
    public Settlement addSpeciesToSettlement(int setId, int speId, BigDecimal population) {
 //      try {
 //          Settlement settlement = settlementDao.findById(setId).orElse(null);
 //          Species species = speciesDao.findById(speId).orElse(null);
 //          if(population != null){
 //
 //          }
 //      }
        return null;
    }

    @Override
    public Settlement addChildSettlementToSettlement(int setId) {
        return null;
    }

    @Override
    public List<Settlement> searchForSettlement(String name) {
        return settlementDao.searchForSettlements(name);
    }
}
