package com.wiley.spaceIRS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettlementServiceImpl implements SettlementService {
    @Autowired
    SettlementDao settlementDao;

    public SettlementServiceImpl(SettlementDao settlementDao){
        this.settlementDao = settlementDao;
    }

    public List<Settlement> getAllSettlements(){
        return null;
    }

}
