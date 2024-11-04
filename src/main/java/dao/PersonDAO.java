package dao;

import model.Person;

import javax.swing.text.Position;
import java.util.List;

public interface PersonDAO {
    List<Person> getAllPersons();
    List<Person> getPersonsByName();
    List<Person> getPersonsBySurname();
    Person getPerson(int id);
    int update(Person p);
    int insert(Person p);
    int delete(Person p);
    int deletePerson(int id);
}