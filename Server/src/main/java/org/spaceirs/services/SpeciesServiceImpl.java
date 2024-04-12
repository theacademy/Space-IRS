package org.spaceirs.services;

import org.spaceirs.dao.SpeciesRepo;
import org.spaceirs.entity.Species;
import org.spaceirs.entity.TaxGroupId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SpeciesServiceImpl implements SpeciesService {

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
    public Species getSpeciesById(int id) throws ServicePersistenceException{
        Species species = speciesRepo.findById(id).orElse(null);
        if(species == null) {
            throw new ServicePersistenceException("Species not found.");
        } else {
            return species;
        }
    }

    @Override
    public Species addNewSpecies(Species species) throws ServicePersistenceException{
        if((species.getName().isEmpty() || species.getName().equals(""))
                || (species.getOrigin() == null || species.getOrigin().equals(""))){
            throw new ServicePersistenceException("Invalid input, a field is missing from species.");
        } else {
            return speciesRepo.save(species);
        }
    }

    @Override
    public Species updateSpeciesData(int id, Species species) throws ServicePersistenceException{
        if(species.getId() != id){
            throw new ServicePersistenceException("Cannot update species, invalid species id given.");
        } else {
            return speciesRepo.save(species);
        }
    }

    @Override
    public void deleteSpeciesById(int id) throws ServicePersistenceException{
        Species species = speciesRepo.findById(id).orElse(null);
        if(species == null){
            throw new ServicePersistenceException("Species not found");
        } else {
            speciesRepo.deleteById(id);
        }
    }

    @Override
    public List<Species> searchForSpecies(String name) {
        return speciesRepo.searchForSpecies(name);
    }
}
