package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {
    private final AccidentRepository store;

    public AccidentService(AccidentRepository store) {
        this.store = store;
    }

    /**
     * Метод добавляет инцидент.
     * @param accident Инцидент.
     */
    @Transactional
    public void add(Accident accident) {
        store.save(accident);
    }

    /**
     * Метод возвращает инцидент по id.
     * @param id Идентификационный номер инцидента.
     * @return Найденный инцидент.
     */
    @Transactional
    public Optional<Accident> findById(int id) {
        return store.findById(id);
    }

    /**
     * Метод возвращает все инциденты.
     * @return Список инцидентов.
     */
    @Transactional
    public List<Accident> findAll() {
        List<Accident> accidents = new ArrayList<>();
        store.findAll().forEach(accidents::add);
        return accidents;
    }
}
