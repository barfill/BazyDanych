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
        if(p == null || p.getId() == 0) {
            throw new IllegalArgumentException("Osoba nie jest poprawnie zainicjalizowana.");
        }

        String query = "INSERT INTO pracownik(imie, nazwisko, stanowisko) values (?, ? ,?)";
        int rowsUpdated = 0;

        try (Connection con = getConnection();
            PreparedStatement pstm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) { //tutaj umożliwiam generowanie autoatmycznych kluczy takich jak przy auto-increment czy właśnie użytym serial

            pstm.setString(1,p.getName());
            pstm.setString(2,p.getSurname());
            pstm.setInt(3,p.getPosition().getId());

            rowsUpdated = pstm.executeUpdate();

            if(rowsUpdated == 0) {
                throw  new SQLException("Nie udało się dodać rekordu.");
            }

            try(ResultSet generatedKey = pstm.getGeneratedKeys()) {
                if(generatedKey.next()) {
                    int generatedId = generatedKey.getInt(1);
                    p.setId(generatedId);
                    return generatedId;
                } else {
                    throw new SQLException("Nie udalo sie pobrac ID dla nowegoo rekordu");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsUpdated;
    }

    @Override
    public int delete(Person p) {
        if(p == null || p.getId() == 0) {
            throw new IllegalArgumentException("Osoba nie jest poprawnie zainicjalizowana.");
        }

        String query = "DELETE FROM pracownik WHERE id=? and imie=? and nazwisko=? and stanowisko=?";
        int rowsUpdated = 0;

        try(Connection con = getConnection();
            PreparedStatement pstm = con.prepareStatement(query)){

            pstm.setInt(1,p.getId());
            pstm.setString(2,p.getName());
            pstm.setString(3,p.getSurname());
            pstm.setInt(4,p.getPosition().getId());

            rowsUpdated = pstm.executeUpdate();

            if(rowsUpdated == 0) {
                throw new SQLException("Taki pracownik nie istnieje w naszym systemie");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsUpdated;
    }

    @Override
    public int deletePerson(int id) {
        if(id == 0) {
            throw new IllegalArgumentException("Osoba nie jest poprawnie zainicjalizowana.");
        }

        String query = "DELETE FROM pracownik WHERE id=?";
        int rowsUpdated = 0;

        try(Connection con = getConnection();
            PreparedStatement pstm = con.prepareStatement(query)){

            pstm.setInt(1,id);

            rowsUpdated = pstm.executeUpdate();

            if(rowsUpdated == 0) {
                throw new SQLException("Pracownik o tkaim ID nie istnieje w naszym systemie");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsUpdated;
    }
}