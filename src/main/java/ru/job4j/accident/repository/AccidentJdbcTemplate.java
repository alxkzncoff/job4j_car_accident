package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Type;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Метод добавляет инцидент в БД.
     * @param accident Инцидент.
     * @return Добавленный инцидент.
     */
    public Accident add(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO accidents (name, text, address, type_id) VALUES (?, ?, ?, ?) RETURNING id",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.getRules().forEach(
                rule -> jdbc.update("INSERT INTO accidents_rules (accident_id, rule_id) VALUES (?, ?)",
                        keyHolder.getKey().intValue(),
                        rule.getId())
        );
        return accident;
    }

    /**
     * Метод обновляет данные инцидента в хранилище.
     * @param id Идентификационный номер инцидента.
     * @param accident Инцидент с новыми данными.
     */
    public Accident update(int id, Accident accident) {
        jdbc.update("UPDATE accidents SET id = ?, name = ?, "
                        + "text = ?, address = ?, type_id = ? WHERE id = ?",
                accident.getId(),
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                id);
        List<Rule> rulesToDelete = new ArrayList<>();
        findAllRules().stream()
                .filter(rule -> !accident.getRules().contains(rule))
                .forEach(rulesToDelete::add);
        rulesToDelete.forEach(
                rule -> jdbc.update("DELETE FROM accidents_rules WHERE accident_id = ? AND rule_id = ?",
                        accident.getId(),
                        rule.getId()));
        jdbc.update("DELETE FROM accidents_rules WHERE accident_id = ?", accident.getId());
        accident.getRules().forEach(
                rule -> jdbc.update("INSERT INTO accidents_rules (accident_id, rule_id) VALUES (?, ?)",
                        accident.getId(),
                        rule.getId())
        );
        return accident;
    }

    /**
     * Метод возвращает тип инцидента по id из БД.
     * @param id Идентификационный номер типа.
     * @return Найденный тип инцидента.
     */
    public Type findTypeById(int id) {
        return jdbc.queryForObject("SELECT id, name FROM types WHERE id = ?",
                (rs, row) -> {
                    Type type = new Type();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                },
                id);
    }

    /**
     * Метод возвращает список всех типов инцидентов из БД.
     * @return Список типов инцидентов.
     */
    public List<Type> findAllTypes() {
        return jdbc.query("SELECT id, name FROM types",
                (rs, row) -> {
                    Type type = new Type();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }

    /**
     * Метод возвращает статью по id из БД.
     * @param id Идентификационный номер статьи.
     * @return Найденная статья.
     */
    public Rule findRuleById(int id) {
        return jdbc.queryForObject("SELECT id, name FROM rules WHERE id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                },
                id);
    }

    /**
     * Метод возвращает список всех статей из БД.
     * @return Список статей.
     */
    public List<Rule> findAllRules() {
        return jdbc.query("SELECT id, name FROM rules",
                (rs, row) -> {
                    Rule  rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    /**
     * Метод возвращает инцидент по id из БД.
     * @param id Идентификационный номер инцидента.
     * @return Найденный инцидент.
     */
    public Accident findById(int id) {
        List<Accident> accidents = findAll();
        return accidents.stream().filter(accident -> accident.getId() == id).findFirst().get();
    }

    /**
     * Метод возвращает список всех инцидентов из БД.
     * @return Список инцидентов.
     */
    public List<Accident> findAll() {
        List<Accident> accidents = jdbc.query("SELECT id, name, text, address, type_id FROM accidents",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    Type type = new Type();
                    type.setId(rs.getInt("type_id"));
                    accident.setType(type);
                    return accident;
                });
        accidents.forEach(accident -> accident.setType(findTypeById(accident.getType().getId())));
        accidents.forEach(accident -> {
            accident.setRules(new HashSet<>());
            List<Integer> ruleIds = jdbc.query("SELECT rule_id FROM accidents_rules WHERE accident_id = ?",
                    (rs, row) -> rs.getInt("rule_id"), accident.getId());
            ruleIds.forEach(id -> accident.getRules().add(findRuleById(id)));
        });
        return accidents;
    }
}
