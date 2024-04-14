package org.spaceirs.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.spaceirs.TestApplicationConfiguration;
import org.spaceirs.entity.Settlement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class SettlementServiceImplTest {

    @Autowired
    SettlementServiceImpl service;

//    @BeforeEach
//    public void setUp() throws ServicePersistenceException {
//        List<Settlement> settlements = service.getAllSettlements();
//        for(Settlement s : settlements) {
//            service.deleteSettlementById(s.getId());
//        }
//    }

    @Test
    void getAllSettlements() throws ServicePersistenceException {
        Settlement settlement1 = new Settlement();
        settlement1.setId(1);
        settlement1.setName("name");
        settlement1.setDirections("direction");
        settlement1.setType("type");
        settlement1.setTaxModifier(BigDecimal.valueOf(3));
        service.addNewSettlement(settlement1);

        Settlement settlement2 = new Settlement();
        settlement2.setId(2);
        settlement2.setName("name");
        settlement2.setDirections("direction");
        settlement2.setType("type");
        settlement2.setTaxModifier(BigDecimal.valueOf(3));
        service.addNewSettlement(settlement2);

        List<Settlement> settlementList = service.getAllSettlements();

        assertEquals(2, settlementList.size());

    }

    @Test
    void addWithValidDataAndGetSettlementByIdTest() throws ServicePersistenceException {

        Settlement settlement1 = new Settlement();
        settlement1.setId(1);
        settlement1.setName("name");
        settlement1.setDirections("direction");
        settlement1.setType("type");
        settlement1.setTaxModifier(BigDecimal.valueOf(3));
        service.addNewSettlement(settlement1);

        Settlement fromDB = service.getSettlementById(1);

        assertEquals("name",fromDB.getName());
    }

    @Test
    void addWithInvalidInputTest() {

        Settlement settlement1 = new Settlement();
        settlement1.setId(1);
        settlement1.setName("");
        settlement1.setDirections("");
        settlement1.setType("");
        settlement1.setTaxModifier(null);

        try {
            service.addNewSettlement(settlement1);
            fail("Expected ValidationException was not thrown.");
        } catch (ServicePersistenceException e) {
           return;
        }

    }

    @Test
    void updateSettlementData() throws ServicePersistenceException {

        Settlement settlement1 = new Settlement();
        settlement1.setId(1);
        settlement1.setName("name");
        settlement1.setDirections("direction");
        settlement1.setType("type");
        settlement1.setTaxModifier(BigDecimal.valueOf(3));
        service.addNewSettlement(settlement1);

        Settlement fromDB = service.getSettlementById(1);

        fromDB.setType("anotherType");


        Settlement updatedSettlement = service.updateSettlementData(1,fromDB);

        assertEquals("anotherType",updatedSettlement.getType());


    }

    @Test
    void deleteSettlementById() {
    }

    @Test
    void addSpeciesToSettlement() {
    }

    @Test
    void addChildSettlementToSettlement() {
    }

    @Test
    void searchForSettlement() {
    }
}