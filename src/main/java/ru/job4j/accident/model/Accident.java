package ru.job4j.accident.model;

import java.util.Objects;

/**
 * Класс правонарушение.
 * @author Aleksandr Kuznetsov.
 * @version 1.0
 */
public class Accident {
    private int id;
    private String name;
    private String text;
    private String address;
    private AccidentType type;

    public Accident(String name, String text, String address, AccidentType type) {
        this.name = name;
        this.text = text;
        this.address = address;
        this.type = type;
    }

    public int getId() {
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AccidentType getType() {
        return type;
    }

    public void setType(AccidentType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Accident: "
                + "id=" + id
                + ", name=" + name
                + ", text=" + text
                + ", address=" + address
                + ", type: " + type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}