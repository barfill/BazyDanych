package model.jpa;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.List;

@Entity
@Table(name = "jednostka")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jednostka_id_gen")
    @SequenceGenerator(name = "jednostka_id_gen", sequenceName = "jednostka_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nazwa", length = Integer.MAX_VALUE)
    private String name;

    @ManyToMany
    @JoinTable(name = "pracjednlnk",
            joinColumns = @JoinColumn(name = "id_jedn"),
            inverseJoinColumns = @JoinColumn(name = "id_prac"))
    private List<Person> members;

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

    public List<Person> getMembers() {
        return members;
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }

}