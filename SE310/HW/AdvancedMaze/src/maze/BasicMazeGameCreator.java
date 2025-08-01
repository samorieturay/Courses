package maze;

public class BasicMazeGameCreator extends MazeGameCreator {
    @Override
    public Room makeRoom(int number) {
        return new Room(number);
    }

    @Override
    public Wall makeWall() {
        return new Wall();
    }

    @Override
    public Door makeDoor(Room r1, Room r2) {
        return new Door(r1, r2);
    }
}