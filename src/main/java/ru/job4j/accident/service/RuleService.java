package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RuleService {
    private final RuleRepository store;

    public RuleService(RuleRepository store) {
        this.store = store;
    }

    /**
     * Метод добавляет статью.
     * @param rule Статья.
     * @return Добавленная статья.
     */
    @Transactional
    public Rule add(Rule rule) {
        return store.save(rule);
    }

    /**
     * Метод возвращает статью по id.
     * @param id Идентификационный номер статьи.
     * @return Найденная статья.
     */
    @Transactional
    public Optional<Rule> findById(int id) {
        return store.findById(id);
    }

    /**
     * Метод возвращает список всех статей.
     * @return Список статей.
     */
    @Transactional
    public List<Rule> findAll() {
        List<Rule> rules = new ArrayList<>();
        store.findAll().forEach(rules::add);
        return rules;
    }
}
