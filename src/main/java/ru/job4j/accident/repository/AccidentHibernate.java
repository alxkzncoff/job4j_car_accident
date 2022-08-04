package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.accident.model.Accident;

import java.util.List;

/**
 * Аннотация @Repository убрана в связи с новой реализацией проекта с помощью Spring Data.
 * Класс не удален в учебных целях.
 */
public class AccidentHibernate implements Store {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Метод добавляет инцидент в БД.
     * @param accident Инцидент.
     * @return Добавленный инцидент.
     */
    public Accident add(Accident accident) {
        tx(
                session -> session.save(accident),
                sf
        );
        return accident;
    }

    /**
     * Метод обновляет данные инцидента в БД.
     * @param accident Инцидент с новыми данными.
     */
    public void update(Accident accident) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            session.update(accident);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * Метод ищет задачу по id БД.
     * @param id Идентификационный номер задачи.
     * @return Найденная задачу или null.
     */
    public Accident findById(int id) {
        return tx(
                session -> (Accident) session.createQuery("SELECT DISTINCT a FROM Accident a "
                                + "JOIN FETCH a.rules WHERE a.id = :aId")
                        .setParameter("aId", id)
                        .uniqueResult(),
                sf
        );
    }

    /**
     * Метод возвращает список всех инцидентов из БД.
     * @return Список инцидентов.
     */
    public List<Accident> findAll() {
        return tx(
                session -> session.createQuery("SELECT DISTINCT a FROM Accident a "
                                + "JOIN FETCH a.rules ").list(),
                sf
        );
    }
}
