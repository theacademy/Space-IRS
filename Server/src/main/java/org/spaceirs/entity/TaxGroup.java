package org.spaceirs.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "taxGroup")
public class TaxGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taxGroupId", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "taxRate")
    private BigDecimal taxRate;

    public TaxGroup() {}

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

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

}
