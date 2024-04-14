package org.spaceirs.entity;

import javax.persistence.*;

@Entity
@Table(name = "populations")
public class Population {

    @EmbeddedId
    private PopulationKey id;

    @ManyToOne
    @MapsId("speciesId")
    @JoinColumn(name = "populations_species_id", referencedColumnName = "speciesId")
    private Species species;

    @ManyToOne
    @MapsId("settlementId")
    @JoinColumn(name = "populations_settlement_id", referencedColumnName = "settlementId")
    private Settlement settlement;

    @Column(name = "population")
    private int population;

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Settlement getSettlement() {
        return settlement;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

}