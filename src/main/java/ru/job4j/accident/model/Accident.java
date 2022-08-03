package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Класс правонарушение.
 * @author Aleksandr Kuznetsov.
 * @version 1.0
 */
@Entity
@Table(name = "accidents")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String text;
    private String address;

    @ManyToOne
    @JoinColumn(name = "type_id", foreignKey = @ForeignKey(name = "TYPE_ID_FK"))
    private Type type;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "accidents_rules", joinColumns = {
            @JoinColumn(name = "accident_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
            @JoinColumn(name = "rule_id", nullable = false, updatable = false)})
    private Set<Rule> rules = new HashSet<>();

    public static Accident of(String name, String text, String address, Type type, Set<Rule> rules) {
        Accident accident = new Accident();
        accident.name = name;
        accident.text = text;
        accident.address = address;
        accident.type = type;
        accident.rules = rules;
        return accident;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
    }

    @Override
    public String toString() {
        return "Accident: "
                + "id=" + id
                + ", name=" + name
                + ", text=" + text
                + ", address=" + address
                + ", type: " + type
                + ", rules: " + rules;
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
