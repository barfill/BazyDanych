package dao.jdbc;

import model.Position;

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

    @Override
    public List<Position> getAllPositions() {
        return null;
    }

    @Override
    public Position getPosition(int id) {
        return null;
    }

    @Override
    public List<Position> getPositionByName(String name) {
        return null;
    }

    @Override
    public int update(Position p) {
        return 0;
    }

    @Override
    public int insert(Position p) {
        return 0;
    }

    @Override
    public int delete(Position p) {
        return 0;
    }

    @Override
    public int deletePosition(int id) {
        return 0;
    }
}