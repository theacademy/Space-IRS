package org.spaceirs.controller;

import org.spaceirs.dao.SpeciesRepo;
import org.spaceirs.entity.Species;
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
    private SpeciesRepo speciesRepo;

    @GetMapping("/species")
    public ResponseEntity<List<Species>> allSpecies() {
        List<Species> species = speciesRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(species);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Species> getSpecies(@PathVariable("id") Integer id) {
        Species species = speciesRepo.findById(id).orElse(null);
        return new ResponseEntity<Species>(species, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addSpecies(@RequestBody Species species) {
        speciesRepo.save(species);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecies(@PathVariable("id") Integer id) {
        speciesRepo.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<Species> updateSpecies(@RequestBody Species species) {
        speciesRepo.save(species);
        return new ResponseEntity<Species>(species, HttpStatus.OK);
    }
}
