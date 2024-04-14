package org.spaceirs.services;

import java.util.List;
import org.spaceirs.dao.PopulationRepo;
import org.spaceirs.entity.Population;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PopulationServiceImpl implements PopulationService {

    @Autowired
    PopulationRepo populationDao;

    public PopulationServiceImpl(PopulationRepo populationDao) {
        this.populationDao = populationDao;
    }

    @Override
    public List<Population> getSettlmentPopulations(int settlementId) throws ServicePersistenceException {
        return populationDao.getSettlementsInhabitants(settlementId);
    }

    @Override
    public List<Population> getSpeciesPopulations(int speciesId) throws ServicePersistenceException {
        return populationDao.getSpeciesHabitats(speciesId);
    }

    @Override
    public Population setPopulation(Population population)
            throws ServicePersistenceException {
        if (population.getPopulation() < 0) {
            throw new ServicePersistenceException("Invalid population number.");
        } else {
            return populationDao.save(population);
        }
    }

    @Override
    public void deletePopulation(int settlementId, int speciesId) throws ServicePersistenceException {
        Population population = populationDao.getPopulation(settlementId, speciesId);
        populationDao.delete(population);
    }

}
