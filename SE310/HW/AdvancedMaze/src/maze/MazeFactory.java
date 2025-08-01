package maze;

import java.io.*;
import java.util.*;

public abstract class MazeFactory {

    // Abstract factory methods
    public abstract Room makeRoom(int number);
    public abstract Wall makeWall();
    public abstract Door makeDoor(Room r1, Room r2);

    public Maze loadMaze(String filename) throws Exception {
        Maze maze = new Maze();
        ArrayList<Room> allRooms = new ArrayList<Room>();
        ArrayList<Door> allDoors = new ArrayList<Door>();
        ArrayList<String> doorNames = new ArrayList<String>();

        // Read file first time to make all rooms
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();

        while (line != null) {
            String[] parts = line.split(" ");
            if (parts[0].equals("room")) {
                int roomNum = Integer.parseInt(parts[1]);
                Room r = makeRoom(roomNum);
                allRooms.add(r);
                maze.addRoom(r);
            }
            line = br.readLine();
        }
        br.close();

        // Read file second time to make all doors
        FileReader fr2 = new FileReader(filename);
        BufferedReader br2 = new BufferedReader(fr2);
        line = br2.readLine();

        while (line != null) {
            String[] parts = line.split(" ");
            if (parts[0].equals("door")) {
                String doorName = parts[1];
                int room1Num = Integer.parseInt(parts[2]);
                int room2Num = Integer.parseInt(parts[3]);

                Room room1 = null;
                Room room2 = null;
                for (Room room : allRooms) {
                    if (room.getNumber() == room1Num) room1 = room;
                    if (room.getNumber() == room2Num) room2 = room;
                }

                Door d = makeDoor(room1, room2);
                allDoors.add(d);
                doorNames.add(doorName);
            }
            line = br2.readLine();
        }
        br2.close();

        // Read file third time to set up room sides
        FileReader fr3 = new FileReader(filename);
        BufferedReader br3 = new BufferedReader(fr3);
        line = br3.readLine();

        while (line != null) {
            String[] parts = line.split(" ");
            if (parts[0].equals("room")) {
                int roomNum = Integer.parseInt(parts[1]);
                String north = parts[2];
                String south = parts[3];
                String east = parts[4];
                String west = parts[5];

                Room currentRoom = null;
                for (Room room : allRooms) {
                    if (room.getNumber() == roomNum) {
                        currentRoom = room;
                        break;
                    }
                }

                // Set each side
                setSide(currentRoom, Direction.North, north, allRooms, allDoors, doorNames);
                setSide(currentRoom, Direction.South, south, allRooms, allDoors, doorNames);
                setSide(currentRoom, Direction.East, east, allRooms, allDoors, doorNames);
                setSide(currentRoom, Direction.West, west, allRooms, allDoors, doorNames);
            }
            line = br3.readLine();
        }
        br3.close();

        maze.setCurrentRoom(0);
        return maze;
    }

    private void setSide(Room currentRoom, Direction direction, String sideValue,
                         ArrayList<Room> allRooms, ArrayList<Door> allDoors, ArrayList<String> doorNames) {
        if (sideValue.equals("wall")) {
            currentRoom.setSide(direction, makeWall());
        } else if (sideValue.startsWith("d")) {
            Door theDoor = null;
            for (int i = 0; i < doorNames.size(); i++) {
                if (doorNames.get(i).equals(sideValue)) {
                    theDoor = allDoors.get(i);
                    break;
                }
            }
            currentRoom.setSide(direction, theDoor);
        } else {
            int otherRoomNum = Integer.parseInt(sideValue);
            Room otherRoom = null;
            for (Room room : allRooms) {
                if (room.getNumber() == otherRoomNum) {
                    otherRoom = room;
                    break;
                }
            }
            currentRoom.setSide(direction, otherRoom);
        }
    }
}