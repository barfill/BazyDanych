package model.jpa;

import jakarta.persistence.*;

@NamedQueries({
        @NamedQuery(name = "PositionGetAll", query="SELECT pos FROM Position pos"),
        @NamedQuery(name = "PositionGetByName", query = "SELECT pos FROM Position pos WHERE pos.name =: name"),
        @NamedQuery(name = "PositionDeleteById", query = "DELETE FROM Position pos WHERE pos.id =: id")
})

@Entity
@Table(name = "stanowisko")
public class Position extends model.Position {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stanowisko_id_gen")
    @SequenceGenerator(name = "stanowisko_id_gen", sequenceName = "stanowisko_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nazwa", length = Integer.MAX_VALUE)
    private String name;

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