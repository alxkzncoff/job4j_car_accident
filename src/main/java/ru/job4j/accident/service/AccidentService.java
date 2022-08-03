package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.List;

@Service
public class AccidentService {
    private final AccidentHibernate store;

    public AccidentService(AccidentHibernate store) {
        this.store = store;
    }

    /**
     * Метод добавляет инцидент.
     * @param accident Инцидент.
     */
    public void add(Accident accident) {
        store.add(accident);
    }

    /**
     * Метод обновляет данные инцидента.
     * @param accident Инцидент с новыми данными.
     */
    public void update(Accident accident) {
        store.update(accident);
    }

    /**
     * Метод возвращает инцидент по id.
     * @param id Идентификационный номер инцидента.
     * @return Найденный инцидент.
     */
    public Accident findById(int id) {
        return store.findById(id);
    }

    /**
     * Метод возвращает все инциденты.
     * @return Список инцидентов.
     */
    public List<Accident> findAll() {
        return store.findAll();
    }
}
