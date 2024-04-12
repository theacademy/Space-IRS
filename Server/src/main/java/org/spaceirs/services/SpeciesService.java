package org.spaceirs.services;

import org.spaceirs.entity.Species;

import java.util.List;

public interface SpeciesService {

    List<Species> getAllSpecies();

    Species getSpeciesById(int id);

    Species addNewSpecies(Species species);

    Species updateSpeciesData(Species species);

    void deleteSpeciesById(int id);

    Species setTaxGroup(Species species, int taxGroup);

    List<Species> searchForSpecies(String name);

}
