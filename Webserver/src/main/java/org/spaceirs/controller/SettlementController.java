package org.spaceirs.controller;

import org.spaceirs.dao.SettlementRepo;
import org.spaceirs.entity.Settlement;
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
    private SettlementRepo settlementRepo;

    @GetMapping("/all")
    public ResponseEntity<List<Settlement>> allSettlements() {
        List<Settlement> settlements = settlementRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(settlements);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Settlement> getSettlement(@PathVariable("id") Integer id) {
        Settlement settlement = settlementRepo.findById(id).orElse(null);
        return new ResponseEntity<Settlement>(settlement, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addSettlement(@RequestBody Settlement settlement) {
        settlementRepo.save(settlement);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSettlement(@PathVariable("id") Integer id) {
        settlementRepo.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Settlement> updateSettlement(@PathVariable("id") Integer id, @RequestBody Settlement settlement) {
        settlementRepo.save(settlement);
        return new ResponseEntity<Settlement>(settlement, HttpStatus.OK);
    }
}
