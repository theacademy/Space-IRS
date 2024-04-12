package org.spaceirs.dao;

import org.spaceirs.entity.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SettlementRepo extends JpaRepository<Settlement, Integer> {
    @Query(value = "SELECT * FROM settlement WHERE `name` LIKE %?1%", nativeQuery = true)
    List<Settlement> searchForSettlements(String name);

    @Query(value = "SELECT ((AVG(tax_group_id) * tax_rate) * tax_modifier) " +
            "FROM tax_group " +
            "INNER JOIN species ON tax_group = tax_group_id " +
            "INNER JOIN populations ON populations_species_id = species_id " +
            "INNER JOIN settlement ON populations_settlement_id = settlement_id " +
            "WHERE species_id = ?1 AND settlement_id = ?2", nativeQuery = true)
    BigDecimal getInhabitantTax(int speciesId, int settlementId);

//    @Query(value = "SELECT ((AVG(tax_group_id) * tax_rate) * tax_modifier) " +
//            "FROM tax_group " +
//            "INNER JOIN species sp ON tax_group = tax_group_id " +
//            "INNER JOIN populations ON populations_species_id = species_id " +
//            "INNER JOIN settlement ON populations_settlement_id = settlement_id " +
//            "WHERE sp.name LIKE %?1% AND settlement_id = ?2", nativeQuery = true)
//    BigDecimal getInhabitantTax(String speciesName, int settlementId);
}
