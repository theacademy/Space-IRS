package org.spaceirs.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PopulationKey implements Serializable {

    @Column(name = "populations_species_id")
    private Long speciesId;

    @Column(name = "populations_settlement_id")
    private Long settlementId;

    public Long getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(Long speciesId) {
        this.speciesId = speciesId;
    }

    public Long getSettlementId() {
        return settlementId;
    }

    public void setSettlementId(Long settlementId) {
        this.settlementId = settlementId;
    }

}