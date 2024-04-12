package org.spaceirs.services;

import org.spaceirs.entity.Settlement;
import org.spaceirs.entity.Species;

import java.util.List;

public interface SpeciesService {

    List<Species> getAllSpecies();
    Species getSpeciesById(int id) throws ServicePersistenceException;
    Species addNewSpecies(Species species) throws ServicePersistenceException;
    Species updateSpeciesData(int id, Species species) throws ServicePersistenceException;
    void deleteSpeciesById(int id) throws ServicePersistenceException;
    Species setTaxGroup(Species species, int taxGroup);



}
