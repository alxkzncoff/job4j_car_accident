package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.List;

/**
 * Аннотация @Repository убрана в связи с новой реализацией проекта с помощью Spring Data.
 * Класс не удален в учебных целях.
 */
public class RuleHibernate implements Store {
    private final SessionFactory sf;

    public RuleHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Метод добавляет статью в БД.
     * @param rule Статья.
     * @return Добавленная статья.
     */
    public Rule add(Rule rule) {
        tx(
                session -> session.save(rule),
                sf
        );
        return rule;
    }

    /**
     * Метод ищет статью по id БД.
     * @param id Идентификационный номер статьи.
     * @return Найденная статья.
     */
    public Rule findById(int id) {
        return tx(
                session -> (Rule) session.createQuery("FROM Rule WHERE id = :rId")
                        .setParameter("rId", id)
                        .uniqueResult(),
                sf
        );
    }

    /**
     * Метод возвращает список всех статей из БД.
     * @return Список статей.
     */
    public List<Rule> findAll() {
        return tx(
                session -> session.createQuery("FROM Rule ").list(),
                sf
        );
    }
}
