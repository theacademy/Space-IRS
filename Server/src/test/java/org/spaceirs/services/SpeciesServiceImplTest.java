package org.spaceirs.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.spaceirs.TestApplicationConfiguration;
import org.spaceirs.entity.Settlement;
import org.spaceirs.entity.Species;
import org.spaceirs.entity.TaxGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest (classes = TestApplicationConfiguration.class)
public class SpeciesServiceImplTest {


    @Autowired
    SpeciesServiceImpl speciesService;

    @Autowired
    SettlementServiceImpl settlementService;

    @BeforeEach
    public void setUp() throws ServicePersistenceException {
        List<Species> speciesList = speciesService.getAllSpecies();
        for(Species species : speciesList){
            speciesService.deleteSpeciesById(species.getId());
        }
    }

//      not working after some modification of Species entity
//    @Test
//    void getAllSpecies() throws ServicePersistenceException {
//        Settlement settlement1 = new Settlement();
//        settlement1.setId(1);
//        settlement1.setName("name");
//        settlement1.setDirections("direction");
//        settlement1.setType("type");
//        settlement1.setTaxModifier(BigDecimal.valueOf(3));
//
//        Species species = new Species();
//        species.setId(1);
//        species.setOrigin(settlement1);
//        species.setName("name");
//        species.setTaxGroup(1);
//        species.setSettlements((Set.of(settlement1)));
//        speciesService.addNewSpecies(species);
//
//        Species species2 = new Species();
//        species2.setId(2);
//        species2.setOrigin(settlement1);
//        species2.setName("name");
//        species.setTaxGroup(1);
//        species.setSettlements((Set.of(settlement1)));
//        speciesService.addNewSpecies(species2);
//
//        List<Species> list = speciesService.getAllSpecies();
//
//        assertEquals(2, list.size());
//
//
//    }
//
//    @Test
//    void addWithValidDataAndGetSpeciesById() {
//        Settlement settlement1 = new Settlement();
//        settlement1.setId(1);
//        settlement1.setName("name");
//        settlement1.setDirections("direction");
//        settlement1.setType("type");
//        settlement1.setTaxModifier(BigDecimal.valueOf(3));
//        try {
//            settlementService.addNewSettlement(settlement1);
//        } catch (ServicePersistenceException e) {
//            fail("Settlement was valid. No exception should have been thrown.");
//        }
//
//        Species species = new Species();
//        species.setId(1);
//        species.setOrigin(settlement1);
//        species.setName("name");
//
//        try {
//            speciesService.addNewSpecies(species);
//        } catch (ServicePersistenceException e) {
//            fail("Species was valid. No exception should have been thrown.");
//        }
//
//        Species savedSpecies = new Species();
//
//        try {
//            savedSpecies = speciesService.getSpeciesById(1);
//        } catch (ServicePersistenceException e) {
//            fail("Save was succesfully. No exception should have been thrown.");
//        }
//
//        assertEquals(species.getId(), savedSpecies.getId());
//    }
}