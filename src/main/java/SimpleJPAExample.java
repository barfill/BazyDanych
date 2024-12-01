import dao.jpa.PersonDAO;
import dao.jpa.PositionDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Person;
import model.jpa.Position;

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
//        pracownikPhy.setName("fizyczny");
//
//        em.getTransaction().begin();
//        jpaDao.update(pracownikPhy);
//        em.getTransaction().commit();


//        model.jpa.Position sprzataczka = new Position();
//        sprzataczka.setName("sprzataczka");
//
//
//
//        em.getTransaction().begin();
//        jpaDao.insert(sprzataczka);
//        em.getTransaction().commit();
//
//        jpaDao.getPositionByName("wozny");
//
//        em.getTransaction().begin();
//        jpaDao.deletePosition(jpaDao.getPosition(19).getId());
//        em.getTransaction().commit();


//        em.getTransaction().begin();
//        jpaDao.deletePosition(18);
//        em.getTransaction().commit();
//
//        for(model.Position p : jpaDao.getPositionByName("developer")) {
//            System.out.println(p.getId());
//        };
//        model.Position pracownik7 = jpaDao.getPosition(7);
//
//        em.getTransaction().begin();
//        jpaDao.delete(pracownik7);
//        em.getTransaction().commit();
//
//        model.Position deletedPosition = jpaDao.getPosition(7);
//        if (deletedPosition == null) {
//            System.out.println("Pozycja została usunięta.");
//        } else {
//            System.out.println("Pozycja nadal istnieje.");
//        }

        PersonDAO jpaPersonDAO = new PersonDAO(em);

//        for(Person p : jpaPersonDAO.getAllPersons()) {
//            System.out.println(p.getName());
//        }
//        for(Person p : jpaPersonDAO.getPersonsByName("Halina")) {
//            System.out.println(p.getId()+" "+p.getName());
//        }
//        for(Person p : jpaPersonDAO.getPersonsBySurname("Król")) {
//            System.out.println(p.getId()+" "+p.getName());
//        }
//        model.jpa.Person jpaPerson = (model.jpa.Person) jpaPersonDAO.getPerson(6);
//        System.out.println(jpaPerson.getId()+" "+jpaPerson.getName()+" "+jpaPerson.getSurname());

//        Person bartosz = jpaPersonDAO.getPerson(6);
//        bartosz.setName("Bartek");
//
//        em.getTransaction().begin();
//        jpaPersonDAO.update(bartosz);
//        em.getTransaction().commit();

//        model.jpa.Person yoshi = new model.jpa.Person();
//        yoshi.setName("Yoshi");
//        yoshi.setSurname("Toranaga");
//
//        em.getTransaction().begin();
//        jpaPersonDAO.insert(yoshi);
//        em.getTransaction().commit();

//        Person John = new Person(); - to nie działa bo klasa model.Person nie jest odpowiednio zmapowana z Hiberantem
//        Person John = new model.jpa.Person();
//        John.setName("John");
//        John.setSurname("Blackthorne");
//
//        em.getTransaction().begin();
//        jpaPersonDAO.insert(John);
//        em.getTransaction().commit();

//        Person wiktoriaCopy = jpaPersonDAO.getPerson(8);
//        em.getTransaction().begin();
//        jpaPersonDAO.delete(wiktoriaCopy);
//        em.getTransaction().commit();
//
//        em.getTransaction().begin();
//        jpaPersonDAO.deletePerson(11);
//        em.getTransaction().commit();







        em.close();
        emf.close();


    }

    //persist nie zwraca żadnej wartości tylko używam gdy chce dodać jakiś nowe nieistniejący w bazce obiekt nie aktualizuje istniejących!!!
    //merge aktualizuje isntiejący obiekt w bazie i zwraca nowy obiekt ale może zarówno dodać jak i zaktaulizować
}