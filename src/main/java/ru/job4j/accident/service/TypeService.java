package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Type;
import ru.job4j.accident.repository.TypeHibernate;

import java.util.List;

@Service
public class TypeService {
    private final TypeHibernate store;

    public TypeService(TypeHibernate store) {
        this.store = store;
    }

    /**
     * Метод добавляет тип инцидента.
     * @param type Тип инцидента.
     * @return Добавленный тип инцидента.
     */
    public Type add(Type type) {
        return store.add(type);
    }

    /**
     * Метод возвращает тип инцидента по id.
     * @param id Идентификационный номер типа.
     * @return Найденный тип инцидента.
     */
    public Type findById(int id) {
        return store.findById(id);
    }

    /**
     * Метод возвращает список типов инцидентов.
     * @return Список типов.
     */
    public List<Type> findAll() {
        return store.findAll();
    }
}
