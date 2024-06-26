package org.spaceirs.entity;

import javax.persistence.*;
import java.math.BigDecimal;

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

}