package dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import model.Person;

import java.util.List;

public class PersonDAO implements dao.PersonDAO{
    @PersistenceContext
    private EntityManager em;

    public PersonDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Person> getAllPersons() {
        Query qn = em.createNamedQuery("PersonsGetAll", model.jpa.Person.class);
        return qn.getResultList();
    }

    @Override
    public List<Person> getPersonsByName(String name) {
        Query qn = em.createNamedQuery("PersonGetByName", model.jpa.Person.class);
        qn.setParameter("name", name);
        return qn.getResultList();
    }

    @Override
    public List<Person> getPersonsBySurname(String surname) {
        Query qn = em.createNamedQuery("PersonGetBySurname", model.jpa.Person.class);
        qn.setParameter("surname", surname);
        return qn.getResultList();
    }

    @Override
    public Person getPerson(int id) {
        return em.find(model.jpa.Person.class, id);
    }

    @Override
    public int update(Person p) {
        em.merge(p);
        return p.getId();
    }

    @Override
    public int insert(Person p) {
        em.persist(p);
        return p.getId();
    }

    @Override
    public int delete(Person p) {
        em.remove(p);
        return p.getId();
    }

    @Override
    public int deletePerson(int id) {
        Query qn = em.createQuery("DELETE FROM Person p where p.id = :id");
        qn.setParameter("id", id);
        int result = qn.executeUpdate();
        return result;
    }
}
