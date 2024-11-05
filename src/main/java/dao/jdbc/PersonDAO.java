package dao.jdbc;

import model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;
import model.Position;

public class PersonDAO implements dao.PersonDAO {
    {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Dotenv dotenv = Dotenv.load(); //ładuje zmienne środowiskowe z pliku .env

    private Connection getConnection() throws SQLException {
        String url = dotenv.get("DB_URL");
        String username = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");

        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        String query = "SELECT p.id as pid, p.imie as pname, p.nazwisko as psurname, s.id as sid ,s.nazwa as sname FROM pracownik p JOIN stanowisko s ON p.stanowisko = s.id;";

        try (Connection con = getConnection();
             PreparedStatement pstm = con.prepareStatement(query);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("pid"));
                person.setName(rs.getString("pname"));
                person.setSurname(rs.getString("psurname"));

                Position position = new Position();
                position.setId(rs.getInt("sid"));
                position.setName(rs.getString("sname"));

                person.setPosition(position);
                persons.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persons;
    }

    @Override
    public List<Person> getPersonsByName(String name) {
        List<Person> persons = new ArrayList<>();
        String query = "SELECT p.id as pid, p.imie as pname, p.nazwisko as psurname, s.id as sid ,s.nazwa as sname FROM pracownik p JOIN stanowisko s ON p.stanowisko = s.id WHERE p.imie ILIKE ?;";

        try (Connection con = getConnection();
             PreparedStatement pstm = con.prepareStatement(query)) {

            pstm.setString(1, "%" + name + "%");

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Person person = new Person();
                    person.setId(rs.getInt("pid"));
                    person.setName(rs.getString("pname"));
                    person.setSurname(rs.getString("psurname"));

                    Position position = new Position();
                    position.setId(rs.getInt("sid"));
                    position.setName(rs.getString("sname"));

                    person.setPosition(position);
                    persons.add(person);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    @Override
    public List<Person> getPersonsBySurname(String surname) {
        List<Person> persons = new ArrayList<>();
        String query = "SELECT p.id as pid, p.imie as pname, p.nazwisko as psurname, s.id as sid ,s.nazwa as sname FROM pracownik p JOIN stanowisko s ON p.stanowisko = s.id WHERE p.nazwisko ILIKE ?;";

        try (Connection con = getConnection();
             PreparedStatement pstm = con.prepareStatement(query)) {

            pstm.setString(1, "%" + surname + "%");

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Person person = new Person();
                    person.setId(rs.getInt("pid"));
                    person.setName(rs.getString("pname"));
                    person.setSurname(rs.getString("psurname"));

                    Position position = new Position();
                    position.setId(rs.getInt("sid"));
                    position.setName(rs.getString("sname"));

                    person.setPosition(position);
                    persons.add(person);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    @Override
    public Person getPerson(int id) {
        Person person = new Person();
        String query = "SELECT p.id as pid, p.imie as pname, p.nazwisko as psurname, s.id as sid ,s.nazwa as sname FROM pracownik p JOIN stanowisko s ON p.stanowisko = s.id WHERE p.id = ?;";

        try(Connection con = getConnection();
            PreparedStatement pstm = con.prepareStatement(query)) {

            pstm.setInt(1,id);

            try(ResultSet rs = pstm.executeQuery()) {
                if(rs.next()) {
                    person.setId(rs.getInt("pid"));
                    person.setName(rs.getString("pname"));
                    person.setSurname(rs.getString("psurname"));

                    Position position = new Position();
                    position.setId(rs.getInt("sid"));
                    position.setName(rs.getString("sname"));

                    person.setPosition(position);
                    //System.out.println(person.getPosition().getName());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public int update(Person p) {
        if(p == null || p.getId() == 0) {
            throw new IllegalArgumentException("Osoba nie jest poprawnie zainicjalizowana.");
        }

        String query = "UPDATE pracownik SET imie = ?, nazwisko = ?, stanowisko = ? WHERE id = ?";
        int rowsUpdated = 0;

        try(Connection con = getConnection();
            PreparedStatement pstm = con.prepareStatement(query)) {

            pstm.setInt(4,p.getId());
            pstm.setString(1,p.getName());
            pstm.setString(2,p.getSurname());
            pstm.setInt(3,p.getPosition().getId());

            rowsUpdated = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsUpdated;
    }

    @Override
    public int insert(Person p) {
        return 0;
    }

    @Override
    public int delete(Person p) {
        return 0;
    }

    @Override
    public int deletePerson(int id) {
        return 0;
    }
}