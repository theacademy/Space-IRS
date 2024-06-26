package org.spaceirs.controller;

import org.spaceirs.entity.Species;
import org.spaceirs.services.ServicePersistenceException;
import org.spaceirs.services.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/species")
@CrossOrigin
public class SpeciesController {
    @Autowired
    private SpeciesService speciesService;

    @GetMapping("/all")
    public ResponseEntity<List<Species>> allSpecies() {
        List<Species> species = speciesService.getAllSpecies();
        return ResponseEntity.status(HttpStatus.OK).body(species);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Species> getSpecies(@PathVariable("id") Integer id) throws ServicePersistenceException {
        Species species = speciesService.getSpeciesById(id);
        return new ResponseEntity<Species>(species, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addSpecies(@RequestBody Species species) throws ServicePersistenceException {
        speciesService.addNewSpecies(species);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSpecies(@PathVariable("id") Integer id) throws ServicePersistenceException {
        speciesService.deleteSpeciesById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Species> updateSpecies(@PathVariable("id") Integer id, @RequestBody Species species) throws ServicePersistenceException {
        speciesService.updateSpeciesData(id, species);
        return new ResponseEntity<Species>(species, HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Species>> updateSettlement(@PathVariable("name") String name)
            throws ServicePersistenceException {
        List<Species> species = speciesService.searchForSpecies(name);
        return ResponseEntity.status(HttpStatus.OK).body(species);
    }
}
