package model;

public class Person {
    private Integer id;
    private String name;
    private String surname;
    private Position position;

    public Person() {
        this.id = 0;
        this.name = "";
        this.surname = "";
    }

    public Person(int id, String name, String surname, Position position) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name='" + name + '\'' + ", surname='" + surname + '\'' + ", position=" + position.getName() + '}';
    }
}