package dao.jdbc;

import io.github.cdimascio.dotenv.Dotenv;
import model.Person;
import model.Position;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionDAO implements dao.PositionDAO{

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
    public List<Position> getAllPositions() {
        List<Position> positions = new ArrayList<>();
        String query = "SELECT s.id as sid, s.nazwa as sname FROM stanowisko s;";

        try (Connection con = getConnection();
             PreparedStatement pstm = con.prepareStatement(query);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Position position = new Position();
                position.setId(rs.getInt("sid"));
                position.setName(rs.getString("sname"));

                positions.add(position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return positions;
    }

    @Override
    public Position getPosition(int id) {
        Position position = new Position();
        String query = "SELECT s.id as sid ,s.nazwa as sname FROM stanowisko s WHERE s.id = ?;";

        try(Connection con = getConnection();
            PreparedStatement pstm = con.prepareStatement(query)) {

            pstm.setInt(1,id);

            try(ResultSet rs = pstm.executeQuery()) {
                if(rs.next()) {
                    position.setId(rs.getInt("sid"));
                    position.setName(rs.getString("sname"));
                    //System.out.println(person.getPosition().getName());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return position;
    }

    @Override
    public List<Position> getPositionByName(String name) {
        List<Position> positions = new ArrayList<>();
        String query = "SELECT s.id as sid ,s.nazwa as sname FROM stanowisko s WHERE s.nazwa ILIKE ?;";

        try (Connection con = getConnection();
             PreparedStatement pstm = con.prepareStatement(query)) {

            pstm.setString(1, "%" + name + "%");

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Position position = new Position();
                    position.setId(rs.getInt("pid"));
                    position.setName(rs.getString("pname"));

                    positions.add(position);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positions;
    }

    @Override
    public int update(Position p) {
        if(p == null || p.getId() == 0) {
            throw new IllegalArgumentException("Stanowisko nie jest poprawnie zainicjalizowane.");
        }

        String query = "UPDATE stanowisko SET nazwa = ? WHERE id = ?";
        int rowsUpdated = 0;

        try(Connection con = getConnection();
            PreparedStatement pstm = con.prepareStatement(query)) {

            pstm.setInt(2,p.getId());
            pstm.setString(1,p.getName());

            rowsUpdated = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsUpdated;
    }

    @Override
    public int insert(Position p) {
        if(p == null || p.getId() == 0) {
            throw new IllegalArgumentException("Stanowisko nie jest poprawnie zainicjalizowane.");
        }

        String query = "INSERT INTO stanowisko(nazwa) values (?)";
        int rowsUpdated = 0;

        try (Connection con = getConnection();
             PreparedStatement pstm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) { //tutaj umożliwiam generowanie autoatmycznych kluczy takich jak przy auto-increment czy właśnie użytym serial

            pstm.setString(1,p.getName());

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
    public int delete(Position p) {
        if(p == null || p.getId() == 0) {
            throw new IllegalArgumentException("Stanowisko nie jest poprawnie zainicjalizowane.");
        }

        String query = "DELETE FROM stanowisko WHERE id=? and nazwa=?";
        int rowsUpdated = 0;

        try(Connection con = getConnection();
            PreparedStatement pstm = con.prepareStatement(query)){

            pstm.setInt(1,p.getId());
            pstm.setString(2,p.getName());

            rowsUpdated = pstm.executeUpdate();

            if(rowsUpdated == 0) {
                throw new SQLException("Takie stanowisko nie istnieje w naszym systemie");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsUpdated;
    }

    @Override
    public int deletePosition(int id) {
        if(id == 0) {
            throw new IllegalArgumentException("Stanowisko nie jest poprawnie zainicjalizowane.");
        }

        String query = "DELETE FROM stanowisko WHERE id=?";
        int rowsUpdated = 0;

        try(Connection con = getConnection();
            PreparedStatement pstm = con.prepareStatement(query)){

            pstm.setInt(1,id);

            rowsUpdated = pstm.executeUpdate();

            if(rowsUpdated == 0) {
                throw new SQLException("Stanowisko o takim ID nie istnieje w naszym systemie");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
//    test
        return rowsUpdated;
    }
}