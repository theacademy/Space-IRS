package org.spaceirs.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.spaceirs.TestApplicationConfiguration;
import org.spaceirs.entity.Settlement;
import org.spaceirs.entity.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest (classes = TestApplicationConfiguration.class)
public class SpeciesServiceImplTest {


    @Autowired
    SpeciesServiceImpl service;

    @BeforeEach
    public void setUp() throws ServicePersistenceException {
        List<Species> speciesList = service.getAllSpecies();
        for(Species species : speciesList){
            service.deleteSpeciesById(species.getId());
        }
    }


    @Test
    void getAllSpecies() throws ServicePersistenceException {
        Settlement settlement1 = new Settlement();
        settlement1.setId(1);
        settlement1.setName("name");
        settlement1.setDirections("direction");
        settlement1.setType("type");
        settlement1.setTaxModifier(BigDecimal.valueOf(3));

        Species species = new Species();
        species.setId(1);
        species.setOrigin(settlement1);
        species.setName("name");
        service.addNewSpecies(species);

        Species species2 = new Species();
        species2.setId(2);
        species2.setOrigin(settlement1);
        species2.setName("name");
        service.addNewSpecies(species2);

        List<Species> list = service.getAllSpecies();

        assertEquals(2, list.size());


    }

    @Test
    void getSpeciesById() throws ServicePersistenceException {
//        Settlement settlement1 = new Settlement();
//        settlement1.setId(1);
//        settlement1.setName("name");
//        settlement1.setDirections("direction");
//        settlement1.setType("type");
//        settlement1.setTaxModifier(BigDecimal.valueOf(3));
//
//        Species species = new Species();
//        species.setId(3);
//        species.setOrigin(settlement1);
//        species.setName("name");
//        service.addNewSpecies(species);
//
//        Species savedSpecies = service.getSpeciesById(3);
//
//        assertEquals(savedSpecies.getId(), species.getId());
    }



    @Test
    void updateSpeciesData() {
    }

    @Test
    void deleteSpeciesById() {
    }

    @Test
    void setTaxGroup() {
    }

    @Test
    void searchForSpecies() {
    }
}