package org.spaceirs.controller;

import org.spaceirs.dao.SpeciesRepo;
import org.spaceirs.entity.Species;
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
    public ResponseEntity<Species> getSpecies(@PathVariable("id") Integer id) {
        Species species = speciesService.getSpeciesById(id);
        return new ResponseEntity<Species>(species, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addSpecies(@RequestBody Species species) {
        speciesService.addNewSpecies(species);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSpecies(@PathVariable("id") Integer id) {
        speciesService.deleteSpeciesById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Species> updateSpecies(@PathVariable("id") Integer id, @RequestBody Species species) {
        speciesService.updateSpeciesData(species);
        return new ResponseEntity<Species>(species, HttpStatus.OK);
    }
}
