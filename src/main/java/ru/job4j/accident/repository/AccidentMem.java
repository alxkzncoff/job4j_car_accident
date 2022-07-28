package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final AtomicInteger id = new AtomicInteger(0);
    private final HashMap<Integer, Accident> accidents = new HashMap<>();
    private final HashMap<Integer, AccidentType> types = new HashMap<>();

    public AccidentMem() {
        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));
    }

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
        accidents.replace(id, accident);
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

    /**
     * Метод возвращает тип инцидента по id из хранилища.
     * @param id Идентификационный номер типа.
     * @return Найденный тип инцидента.
     */
    public AccidentType findTypeById(int id) {
        return types.get(id);
    }

    /**
     * Метод возвращает список типов инцидентов из хранилища.
     * @return Список типов.
     */
    public List<AccidentType> findAllTypes() {
        return types.values().stream().toList();
    }
}