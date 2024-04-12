package org.spaceirs.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "settlement")
public class Settlement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "settlementId", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "directions")
    private String directions;

    @Column(name = "taxModifier")
    private BigDecimal taxModifier;

    @ElementCollection
    @CollectionTable(name = "populations", joinColumns = @JoinColumn(name = "populations_settlement_id"))
    @MapKeyJoinColumn(name = "populations_species_id")
    @Column(name = "population")
    private Map<Species, Integer> inhabitants;

    public Settlement() {}

    public Settlement(Species species, int populationData) {
        this.inhabitants.put(species, populationData);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public BigDecimal getTaxModifier() {
        return taxModifier;
    }

    public void setTaxModifier(BigDecimal taxModifier) {
        this.taxModifier = taxModifier;
    }

    public Map<Species, Integer> getInhabitants() {
        return inhabitants;
    }
}