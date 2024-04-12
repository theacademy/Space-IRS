package org.spaceirs.entity;

import javax.persistence.*;
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
    private Integer taxGroup;

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

    @Override
    public String toString() {
        return "Species{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
