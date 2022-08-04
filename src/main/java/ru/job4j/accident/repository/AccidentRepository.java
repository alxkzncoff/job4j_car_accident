package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Optional;

@Repository
public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Query("SELECT DISTINCT a FROM Accident a JOIN FETCH a.type JOIN FETCH a.rules WHERE a.id = ?1")
    Optional<Accident> findById(int id);

    @Query("SELECT DISTINCT a FROM Accident a JOIN FETCH a.type JOIN FETCH a.rules")
    Iterable<Accident> findAll();
}
