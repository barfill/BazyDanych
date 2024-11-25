package model;

public class Position {
    private Integer id;
    private String name;

    public Position() {
        this.id = 0;
        this.name = "";
    }

    public Position(int id, String name) {
        this.id = id;
        this.name = name;
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
}