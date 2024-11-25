package model.jpa;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "PersonsGetAll", query="SELECT p FROM Person p"),
        @NamedQuery(name = "PersonGetByName", query = "SELECT p FROM Person p WHERE p.name =: name"),
        @NamedQuery(name = "PersonGetBySurname", query = "SELECT p FROM Person p WHERE p.surname =: surname")
})

@Entity
@Table(name = "pracownik")
public class Person extends model.Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pracownik_id_gen")
    @SequenceGenerator(name = "pracownik_id_gen", sequenceName = "pracownik_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "imie", length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "nazwisko", length = Integer.MAX_VALUE)
    private String surname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stanowisko")
    private Position position;

    @ManyToMany(mappedBy = "members")
    private List<Unit> units;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

}