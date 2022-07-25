package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.List;

@Repository
public class AccidentMem {
    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        accidents.put(1, new Accident(1, "Превышение скорости",
                "Превышение скорости на 20-40 км/ч.", "г. Санкт-Петербург"));
        accidents.put(2, new Accident(2, "Парковка в неположенном месте",
                "Парковка на месте для инвалидов.", "г. Санкт-Петербург"));
        accidents.put(3, new Accident(3, "Нарушение разметки",
                "Выезд через двойную сплошную на встречную полосу.", "г. Санкт-Петербург"));
    }

    public void add(int id, Accident accident) {
        accidents.put(id, accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public List<Accident> findAll() {
        return accidents.values().stream().toList();
    }
}
