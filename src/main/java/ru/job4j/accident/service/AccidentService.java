package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@Service
public class AccidentService {
    private final AccidentMem store;

    public AccidentService(AccidentMem store) {
        this.store = store;
    }

    public void add(Accident accident) {
        store.add(accident);
    }

    public Accident findById(int id) {
        return store.findById(id);
    }

    public List<Accident> findAll() {
        return store.findAll();
    }
}
