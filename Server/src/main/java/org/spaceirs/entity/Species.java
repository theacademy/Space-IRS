package org.spaceirs.entity;

import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "species")
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "speciesId", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "origin", referencedColumnName = "settlementId")
    private Settlement origin;

    @Column(name = "taxGroup")
    private int taxGroup; //id only

//    @ManyToOne
//    @JoinColumn(name = "taxGroup", referencedColumnName = "taxGroupId")
//    private TaxGroup taxGroup; //full tax group entity

    @ManyToMany(mappedBy = "inhabitants")
    private Set<Settlement> settlements;

    public Species() {}

    public Species(Set<Settlement> settlements) {
        this.settlements = settlements;
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

    public Settlement getOrigin() {
        return origin;
    }

    public void setOrigin(Settlement origin) {
        this.origin = origin;
    }

    public int getTaxGroup() {
        return taxGroup;
    }

    public void setTaxGroup(int taxGroup) {
        this.taxGroup = taxGroup;
    }

//    public int getTaxGroupId() {
//        return taxGroupId;
//    }
//
//    public void setTaxGroupId(int taxGroupId) {
//        this.taxGroupId = taxGroupId;
//    }

}
