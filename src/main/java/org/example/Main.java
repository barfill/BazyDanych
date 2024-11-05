package org.example;

import dao.jdbc.DAO;
import dao.jdbc.PersonDAO;
import model.Person;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PersonDAO personDAO = new PersonDAO();
        List<Person> personList = new ArrayList<>();

        try {
            personList = personDAO.getAllPersons();
            personList.forEach(person -> {
                System.out.println(person);
            });

            System.out.println(personDAO.getPersonsByName("Tom"));
            System.out.println(personDAO.getPersonsBySurname("Stef"));
            System.out.println(personDAO.getPerson(4));

//            Person personToUpdate = personDAO.getPerson(4);
//            personToUpdate.setName("Aliszja");
//
//            int rowsUpdated = personDAO.update(personToUpdate);
//            System.out.println("Zaktualizowano: "+rowsUpdated+" wierszy");



        } catch (Exception e) {
            System.err.println("Błąd: " + e.getMessage());
        }
    }
}