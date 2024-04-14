package org.spaceirs.services;

import org.spaceirs.dao.SpeciesRepo;
import org.spaceirs.entity.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpeciesServiceImpl implements SpeciesService {

    @Autowired
    SpeciesRepo speciesDao;

    public SpeciesServiceImpl(SpeciesRepo speciesRepo) {
        this.speciesDao = speciesRepo;
    }

    @Override
    public List<Species> getAllSpecies() {
        return speciesDao.findAll();
    }

    @Override
    public Species getSpeciesById(int id) throws ServicePersistenceException {
        Species species = speciesDao.findById(id).orElse(null);
        if (species == null) {
            throw new ServicePersistenceException("Species not found.");
        } else {
            return species;
        }
    }

    @Override
    public Species addNewSpecies(Species species) throws ServicePersistenceException {
        if ((species.getName().isEmpty() || species.getName().equals(""))
                || (species.getOrigin() == null || species.getOrigin().equals(""))) {
            throw new ServicePersistenceException("Invalid input, a field is missing from species.");
        } else {
            return speciesDao.save(species);
        }
    }

    @Override
    public Species updateSpeciesData(int id, Species species) throws ServicePersistenceException {
        if (species.getId() != id) {
            throw new ServicePersistenceException("Cannot update species, invalid species id given.");
        } else {
            return speciesDao.save(species);
        }
    }

    @Override
    public void deleteSpeciesById(int id) throws ServicePersistenceException {
        Species species = speciesDao.findById(id).orElse(null);
        if (species == null) {
            throw new ServicePersistenceException("Species not found");
        } else {
            speciesDao.deleteById(id);
        }
    }

    @Override
    public List<Species> searchForSpecies(String name) {
        return speciesDao.searchForSpecies(name);
    }
}
