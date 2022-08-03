package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleHibernate;

import java.util.List;

@Service
public class RuleService {
    private final RuleHibernate store;

    public RuleService(RuleHibernate store) {
        this.store = store;
    }

    /**
     * Метод добавляет статью.
     * @param rule Статья.
     * @return Добавленная статья.
     */
    public Rule add(Rule rule) {
        return store.add(rule);
    }

    /**
     * Метод возвращает статью по id.
     * @param id Идентификационный номер статьи.
     * @return Найденная статья.
     */
    public Rule findById(int id) {
        return store.findById(id);
    }

    /**
     * Метод возвращает список всех статей.
     * @return Список статей.
     */
    public List<Rule> findAll() {
        return store.findAll();
    }
}
