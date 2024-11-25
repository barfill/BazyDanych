import dao.jpa.PositionDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.jpa.Person;
import model.jpa.Position;
import model.jpa.Unit;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class SimpleJPAExample {

    public static void main(String[] args) {
        /*
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appdb");
        EntityManager em = emf.createEntityManager();
        Position position = em.find(Position.class, 1);
        System.out.println(position.getName() +" "+position.getId());
        Position positionPhy = em.find(Position.class,4);
        System.out.println(positionPhy.getName() +" "+positionPhy.getId());
        Person p = em.find(Person.class, 3);
        System.out.println(p.getId() + " "+p.getSurname());
        for( Unit j : p.getUnits()){
            System.out.println(j.getName());
            System.out.println(j.getId());

            for(model.Person per : j.getMembers()) {
                System.out.println(per.getName() + " "+per.getSurname());
            }
        }
        Position pos = new Position();
        pos.setName("developer");
        System.out.println(pos);
        em.getTransaction().begin();
        em.persist(pos);
        //pos = em.merge(pos); - zwraca obiekt wiec trzeba tak wywolac
        em.getTransaction().commit();
        System.out.println(pos);
        Person p1 = new Person();
        p1.setName("Halina");
        p1.setSurname("Łódzka");
        p1.setPosition(pos);
        Unit j = em.find(Unit.class,1);
        List<Unit> jedn = new ArrayList<>();
        jedn.add(j);
        p1.setUnits(jedn);
        j.getMembers().add(p1);
        em.getTransaction().begin();
        em.persist(p1);
        em.getTransaction().commit();
        em.close();
        emf.close();
         */

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("appdb");
        EntityManager em = emf.createEntityManager();

        PositionDAO jpaDao = new PositionDAO(em);

//        for(model.Position p : jpaDao.getAllPositions()) {
//            System.out.println(p.getName());
//        }
//
//        model.Position pracownikPhy = jpaDao.getPosition(4);
//        pracownikPhy.setName("fizycznyy");

//        em.getTransaction().begin();
//        jpaDao.update(pracownikPhy);
//        em.getTransaction().commit();


//        Position wozny = new Position();
//        wozny.setName("wozny");
//
//
//        em.getTransaction().begin();
//        jpaDao.update(wozny);
//        em.getTransaction().commit();

//        jpaDao.getPositionByName("wozny");
//
//        em.getTransaction().begin();
//        jpaDao.delete(wozny);
//        em.getTransaction().commit();

//
//        em.getTransaction().begin();
//        jpaDao.deletePosition(13);
//        em.getTransaction().commit();
//
//        for(model.Position p : jpaDao.getPositionByName("developer")) {
//            System.out.println(p.getId());
//        };
        model.Position pracownik7 = jpaDao.getPosition(7);

        em.getTransaction().begin();
        jpaDao.delete(pracownik7);
        em.getTransaction().commit();

        model.Position deletedPosition = jpaDao.getPosition(7);
        if (deletedPosition == null) {
            System.out.println("Pozycja została usunięta.");
        } else {
            System.out.println("Pozycja nadal istnieje.");
        }

        em.close();
        emf.close();


    }

    //persist nie zwraca żadnej wartości tylko używam gdy chce dodać jakiś nowe nieistniejący w bazce obiekt nie aktualizuje istniejących!!!
    //merge aktualizuje isntiejący obiekt w bazie i zwraca nowy obiekt ale może zarówno dodać jak i zaktaulizować
}