package ru.job4j.accident.repository;


import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class AccidentMemTest {

    @Test
    public void findById() {
        Accident accident = new Accident("accident", "description", "address",
                new AccidentType(), new HashSet<>());
        accident.setId(1);
        AccidentMem store = new AccidentMem();
        store.add(accident);
        assertThat(accident).isEqualTo(store.findById(1));
    }

    @Test
    public void whenFindAll() {
        Accident acc1 = new Accident("accident 1", "description 1", "address 1",
                new AccidentType(), new HashSet<>());
        Accident acc2 = new Accident("accident 2", "description 2", "address 2",
                new AccidentType(), new HashSet<>());
        acc1.setId(1);
        acc1.setId(2);
        AccidentMem store = new AccidentMem();
        store.add(acc1);
        store.add(acc2);
        assertThat(store.findAll().size()).isEqualTo(2);
        assertThat(store.findById(2)).isEqualTo(acc2);
    }
}