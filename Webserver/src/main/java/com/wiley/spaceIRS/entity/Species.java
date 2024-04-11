package com.wiley.spaceIRS.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "species")
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "origin")
    private int origin;

    @Column(name = "tax_group")

    @ManyToOne
    @JoinColumn(name = "tax_id")
    TaxGroup taxGroup;

    @ManyToMany
    @JoinTable(
            name = "populations",
            joinColumns = {@JoinColumn(name = "species_id")},
            inverseJoinColumns = {@JoinColumn(name = "settlement_id")}
    )
    private Set<Settlement> settlements;

    public Species() {}

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
}