package org.spaceirs.controller;

import java.util.List;

import org.spaceirs.entity.Population;
import org.spaceirs.services.PopulationService;
import org.spaceirs.services.ServicePersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/populations")
@CrossOrigin
public class PopulationController {

    @Autowired
    private PopulationService populationService;

    @GetMapping("/settlement/{id}")
    public ResponseEntity<List<Population>> getSettlementPopulations(@PathVariable("id") Integer id)
            throws ServicePersistenceException {
        List<Population> populations = populationService.getSettlmentPopulations(id);
        return ResponseEntity.status(HttpStatus.OK).body(populations);
    }

    @GetMapping("/species/{id}")
    public ResponseEntity<List<Population>> getSpeciesPopulations(@PathVariable("id") Integer id)
            throws ServicePersistenceException {
        List<Population> populations = populationService.getSpeciesPopulations(id);
        return ResponseEntity.status(HttpStatus.OK).body(populations);
    }

    @GetMapping("/set")
    public ResponseEntity<Void> setPopulation(@RequestBody Population population) throws ServicePersistenceException {
        populationService.setPopulation(population);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{settlementId}/{speciesId}")
    public ResponseEntity<Void> deletePopulation(@PathVariable("settlementId") Integer settlementId,
            @PathVariable("speciesId") Integer speciesId) throws ServicePersistenceException {
        populationService.deletePopulation(settlementId, speciesId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
