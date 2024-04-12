package org.spaceirs.services;

import org.spaceirs.entity.Species;
import org.spaceirs.entity.TaxGroupId;

import java.util.List;

public interface SpeciesService {

    List<Species> getAllSpecies();

    Species getSpeciesById(int id);

    Species addNewSpecies(Species species);

    Species updateSpeciesData(Species species);

    void deleteSpeciesById(int id);

    List<Species> searchForSpecies(String name);

}
