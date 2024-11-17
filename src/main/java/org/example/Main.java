package org.example;

import dao.jdbc.DAO;
import dao.jdbc.PersonDAO;
import model.Person;
import model.Position;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PersonDAO personDAO = new PersonDAO();
        List<Person> personList = new ArrayList<>();

        try {
//            personList = personDAO.getAllPersons();
//            personList.forEach(person -> {
//                System.out.println(person);
//            });

//            System.out.println(personDAO.getPersonsByName("Tom"));
//            System.out.println(personDAO.getPersonsBySurname("Stef"));
//            System.out.println(personDAO.getPerson(4));
//
//            Person personToUpdate = personDAO.getPerson(4);
//            personToUpdate.setName("Alisz");
//
//            int rowsUpdated = personDAO.update(personToUpdate);
//            System.out.println("Zaktualizowano: "+rowsUpdated+" wierszy");
//            System.out.println(personDAO.getPerson(4));

            Person p1 = new Person(1,"Wiktoria","Król",new Position(3,"dyrektor"));
            System.out.println(p1);
//
//            int insertedPerson = personDAO.insert(p1);
//            System.out.println("Dodano osobe o ID:"+insertedPerson);

            System.out.println(p1);

            int deletedPerson = personDAO.delete(p1);
            System.out.println("Usunięto osobe o ID:"+deletedPerson);



        } catch (Exception e) {
            System.err.println("Błąd: " + e.getMessage());
        }
    }
}