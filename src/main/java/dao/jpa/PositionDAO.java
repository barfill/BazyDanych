package dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import model.Position;

import java.util.List;

public class PositionDAO implements dao.PositionDAO {
    @PersistenceContext
    private EntityManager em;

    public PositionDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Position> getAllPositions() {
        Query qn = em.createNamedQuery("PositionGetAll", model.jpa.Position.class);
        return qn.getResultList();
    }

    @Override
    public Position getPosition(int id) {
        return em.find(model.jpa.Position.class, id);
    }

    @Override
    public List<Position> getPositionByName(String name) {
        Query qn = em.createNamedQuery("PositionGetByName", model.jpa.Position.class);
        qn.setParameter("name", name);
        return qn.getResultList();
    }

    @Override
    public int update(Position p) {
        em.merge(p);
        return p.getId();
    }

    @Override
    public int insert(Position p) {
        em.persist(p);
        return p.getId();
    }

    @Override
    public int delete(Position p) {
        Query query = em.createQuery("UPDATE Person p SET p.position = NULL WHERE p.position = :position");
        query.setParameter("position", p);
        query.executeUpdate();
        //To jest usuniecie powiązać do danej pozycji w innych tabelach, bez tego nie da się usunać pozycji która jest powiązana

        em.remove(p);
        return p.getId();
    }

    @Override
    public int deletePosition(int id) {
        Query query = em.createQuery("UPDATE Person p SET p.position = NULL WHERE p.position.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        //To jest usuniecie powiązać do danej pozycji w innych tabelach, bez tego nda się usunać pozycje która jest powiązana ale lepiej tutaj też usuwac żeby nie było konfliktów, że osoba ma przypisaną pozycję o niestniejącym ID

        Query qn = em.createNamedQuery("PositionDeleteById");
        qn.setParameter("id", id);
        int result = qn.executeUpdate();
        return result;
    }
}
