package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Type;

import java.util.List;

@Repository
public class TypeHibernate implements Store {
    private final SessionFactory sf;

    public TypeHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Метод добавляет тип инцидента в БД.
     * @param type Тип инцидента.
     * @return Добавленный тип инцидента.
     */
    public Type add(Type type) {
        tx(
                session -> session.save(type),
                sf
        );
        return type;
    }

    /**
     * Метод ищет тип инцидента по id БД.
     * @param id Идентификационный номер типа.
     * @return Найденный тип инцидента.
     */
    public Type findById(int id) {
        return tx(
                session -> (Type) session.createQuery("FROM Type WHERE id = :tId")
                        .setParameter("tId", id)
                        .uniqueResult(),
                sf
        );
    }

    /**
     * Метод возвращает список типов инцидента из БД.
     * @return Список типов инцидента.
     */
    public List<Type> findAll() {
        return tx(
                session -> session.createQuery("FROM Type ").list(),
                sf
        );
    }
}
