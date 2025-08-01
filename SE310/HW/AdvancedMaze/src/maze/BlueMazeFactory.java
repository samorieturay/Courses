package maze;

public class BlueMazeFactory extends MazeFactory {
    @Override
    public Room makeRoom(int number) {
        return new GreenRoom(number);
    }

    @Override
    public Wall makeWall() {
        return new BlueWall();
    }

    @Override
    public Door makeDoor(Room r1, Room r2) {
        return new BrownDoor(r1, r2);
    }
}