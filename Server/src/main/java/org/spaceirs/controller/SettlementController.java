package org.spaceirs.controller;

import org.spaceirs.entity.Settlement;
import org.spaceirs.services.ServicePersistenceException;
import org.spaceirs.services.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settlement")
@CrossOrigin
public class SettlementController {
    @Autowired
    private SettlementService settlementService;

    @GetMapping("/all")
    public ResponseEntity<List<Settlement>> allSettlements() {
        List<Settlement> settlements = settlementService.getAllSettlements();
        return ResponseEntity.status(HttpStatus.OK).body(settlements);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Settlement> getSettlement(@PathVariable("id") Integer id) throws ServicePersistenceException {
        Settlement settlement = settlementService.getSettlementById(id);
        return new ResponseEntity<Settlement>(settlement, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addSettlement(@RequestBody Settlement settlement) throws ServicePersistenceException {
        settlementService.addNewSettlement(settlement);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSettlement(@PathVariable("id") Integer id) throws ServicePersistenceException {
        settlementService.deleteSettlementById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Settlement> updateSettlement(@PathVariable("id") Integer id,
            @RequestBody Settlement settlement) throws ServicePersistenceException {
        settlementService.updateSettlementData(id, settlement);
        return new ResponseEntity<Settlement>(settlement, HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Settlement>> updateSettlement(@PathVariable("name") String name) throws ServicePersistenceException {
        List<Settlement> settlements = settlementService.searchForSettlement(name);
        return ResponseEntity.status(HttpStatus.OK).body(settlements);
    }
}
