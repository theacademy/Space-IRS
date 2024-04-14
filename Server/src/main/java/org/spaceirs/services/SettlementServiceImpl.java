package org.spaceirs.services;

import org.spaceirs.dao.SettlementRepo;
import org.spaceirs.entity.Settlement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SettlementServiceImpl implements SettlementService {
    @Autowired
    SettlementRepo settlementDao;

    public SettlementServiceImpl(SettlementRepo settlementDao) {
        this.settlementDao = settlementDao;
    }

    public List<Settlement> getAllSettlements() {
        return settlementDao.findAll();
    }

    @Override
    public Settlement getSettlementById(int id) throws ServicePersistenceException {
        Settlement settlement = settlementDao.findById(id).orElse(null);
        if (settlement == null) {
            throw new ServicePersistenceException("Settlement not found.");
        } else {
            return settlement;
        }
    }

    @Override
    public Settlement addNewSettlement(Settlement settlement) throws ServicePersistenceException {
        if ((settlement.getName().isEmpty() || settlement.getName().equals(""))
                || (settlement.getDirections().isEmpty() || settlement.getDirections().equals(""))
                || (settlement.getType().isEmpty() || settlement.getType().equals(""))
                || (settlement.getTaxModifier() == null || settlement.getTaxModifier().equals(""))) {

            throw new ServicePersistenceException("Invalid input, a filed is missing from settlement.");

        } else {
            settlement = settlementDao.save(settlement);
        }
        return settlement;
    }

    @Override
    public Settlement updateSettlementData(int id, Settlement settlement) throws ServicePersistenceException {
        if (settlement.getId() != id) {
            throw new ServicePersistenceException("Cannot update settlement, invalid settlement id given.");
        } else {
            return settlementDao.save(settlement);
        }

    }

    @Override
    public void deleteSettlementById(int id) throws ServicePersistenceException {
        Settlement settlement = settlementDao.findById(id).orElse(null);
        // if(settlement == null) {
        // throw new ServicePersistenceException("Settlement not found");
        // } else {
        settlementDao.delete(settlement);
        // }
    }

    @Override
    public List<Settlement> searchForSettlement(String name) {
        return settlementDao.searchForSettlements(name);
    }

    @Override
    public BigDecimal getInhabitantTax(int speciesId, int settlementId) {
        return settlementDao.getInhabitantTax(speciesId, settlementId);
    }
}
