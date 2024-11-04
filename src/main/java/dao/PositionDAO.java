package dao;

import java.util.List;
import model.Position;

public interface PositionDAO {
    List<Position> getAllPositions();
    Position getPosition(int id);
    List<Position> getPositionByName(String name);
    int update(Position p);
    int insert(Position p);
    int delete(Position p);
    int deletePosition(int id);

}