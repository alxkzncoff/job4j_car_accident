package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final AtomicInteger id = new AtomicInteger(0);
    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    /**
     * Метод добавляет инцидент в хранилище.
     * @param accident Инцидент.
     */
    public void add(Accident accident) {
        accident.setId(id.incrementAndGet());
        accidents.put(accident.getId(), accident);
    }

    /**
     * Метод обновляет данные инцидента в хранилище.
     * @param id Идентификационный номер инцидента.
     * @param accident Инцидент с новыми данными.
     */
    public void update(int id, Accident accident) {
        accidents.put(id, accident);
    }

    /**
     * Метод возвращает инцидент по id из хранилища.
     * @param id Идентификационный номер инцидента.
     * @return Найденный инцидент.
     */
    public Accident findById(int id) {
        return accidents.get(id);
    }

    /**
     * Метод возвращает все инциденты из хранилища.
     * @return Список инцидентов.
     */
    public List<Accident> findAll() {
        return accidents.values().stream().toList();
    }
}
