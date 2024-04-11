package org.spaceirs.services;

import org.spaceirs.dao.SpeciesRepo;
import org.spaceirs.entity.Settlement;
import org.spaceirs.entity.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesServiceImpl implements SpeciesService{

    @Autowired
    SpeciesRepo speciesRepo;

    public SpeciesServiceImpl(SpeciesRepo speciesRepo) {
        this.speciesRepo = speciesRepo;
    }

    @Override
    public List<Species> getAllSpecies() {
        return speciesRepo.findAll();
    }

    @Override
    public Species getSpeciesById(int id) {
        return speciesRepo.findById(id).orElse(null);
    }

    @Override
    public Species addNewSpecies(Species species, Settlement origin) {
        return speciesRepo.save(species);
    }

    @Override
    public Species updateSpeciesData(Species species) {
        return speciesRepo.save(species);
    }

    @Override
    public void deleteSpeciesById(int id) {
        speciesRepo.deleteById(id);
    }

    @Override
    public Species setTaxGroup(Species species, int taxGroup) {
        //TODO IMPLEMENT CALCULATING
        return null;
    }
}
