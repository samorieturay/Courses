package maze;

public class RedMazeFactory extends MazeFactory {
    @Override
    public Room makeRoom(int number) {
        return new PinkRoom(number);
    }

    @Override
    public Wall makeWall() {
        return new RedWall();
    }

    @Override
    public Door makeDoor(Room r1, Room r2) {
        return new Door(r1, r2);
    }
}
