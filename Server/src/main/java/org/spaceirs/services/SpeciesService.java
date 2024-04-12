package org.spaceirs.services;

import org.spaceirs.entity.Species;
import org.spaceirs.entity.TaxGroupId;

import java.util.List;

public interface SpeciesService {

    List<Species> getAllSpecies();

    Species getSpeciesById(int id)throws ServicePersistenceException;

    Species addNewSpecies(Species species)throws ServicePersistenceException;

    Species updateSpeciesData(int id, Species species)throws ServicePersistenceException;

    void deleteSpeciesById(int id)throws ServicePersistenceException;

    List<Species> searchForSpecies(String name);

}
