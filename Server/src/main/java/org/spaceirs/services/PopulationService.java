package org.spaceirs.services;

import org.spaceirs.entity.Population;
import java.util.List;

public interface PopulationService {

    List<Population> getSettlmentPopulations(int settlementId) throws ServicePersistenceException;

    List<Population> getSpeciesPopulations(int speciesId) throws ServicePersistenceException;

    Population setPopulation(Population population) throws ServicePersistenceException;

    void deletePopulation(int settlementId, int speciesId) throws ServicePersistenceException;
}
