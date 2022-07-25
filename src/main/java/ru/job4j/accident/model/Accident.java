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

    public Accident() {
    }

    public Accident(int id, String name, String text, String address) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Accident: "
                + "id=" + id
                + ", name=" + name
                + ", text=" + text
                + ", address=" + address;
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
