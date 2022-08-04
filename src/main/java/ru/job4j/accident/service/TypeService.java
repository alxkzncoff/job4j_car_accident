package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Type;
import ru.job4j.accident.repository.TypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TypeService {
    private final TypeRepository store;

    public TypeService(TypeRepository store) {
        this.store = store;
    }

    /**
     * Метод добавляет тип инцидента.
     * @param type Тип инцидента.
     * @return Добавленный тип инцидента.
     */
    @Transactional
    public Type add(Type type) {
        return store.save(type);
    }

    /**
     * Метод возвращает тип инцидента по id.
     * @param id Идентификационный номер типа.
     * @return Найденный тип инцидента.
     */
    @Transactional
    public Optional<Type> findById(int id) {
        return store.findById(id);
    }

    /**
     * Метод возвращает список типов инцидентов.
     * @return Список типов.
     */
    @Transactional
    public List<Type> findAll() {
        List<Type> types = new ArrayList<>();
        store.findAll().forEach(types::add);
        return types;
    }
}
