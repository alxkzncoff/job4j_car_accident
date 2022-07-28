package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@Service
public class AccidentService {
    private final AccidentMem store;

    public AccidentService(AccidentMem store) {
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
     * @param id Идентификационный номер инцидента.
     * @param accident Инцидент с новыми данными.
     */
    public void update(int id, Accident accident) {
        store.update(id, accident);
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

    /**
     * Метод возвращает тип инцидента по id.
     * @param id Идентификационный номер типа.
     * @return Найденный тип инцидента.
     */
    public AccidentType findTypeById(int id) {
        return store.findTypeById(id);
    }

    /**
     * Метод возвращает список типов инцидентов.
     * @return Список типов.
     */
    public List<AccidentType> findAllTypes() {
        return store.findAllTypes();
    }

    /**
     * Метод возвращает статью по id.
     * @param id Идентификационный номер статьи.
     * @return Найденная статья.
     */
    public Rule findRuleById(int id) {
        return store.findRuleById(id);
    }

    /**
     * Метод возвращает список всех статей.
     * @return Список статей.
     */
    public List<Rule> findAllRules() {
        return store.findAllRules();
    }
}
