/*
 * SimpleMazeGame.java
 * Copyright (c) 2008, Drexel University.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Drexel University nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY DREXEL UNIVERSITY ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL DREXEL UNIVERSITY BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package maze;

import maze.ui.MazeViewer;
import java.io.*;
import java.util.*;
/**
 *
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */
public abstract class MazeGameCreator {

	// Factory methods to be implemented by subclasses
	public abstract Room makeRoom(int number);
	public abstract Wall makeWall();
	public abstract Door makeDoor(Room r1, Room r2);

	public static Maze createMaze(MazeGameCreator creator) {
		Maze maze = new Maze();

		Room r0 = creator.makeRoom(0);
		Room r1 = creator.makeRoom(1);
		Door d0 = creator.makeDoor(r0, r1);

		r0.setSide(Direction.North, creator.makeWall());
		r0.setSide(Direction.South, d0);
		r0.setSide(Direction.East, creator.makeWall());
		r0.setSide(Direction.West, creator.makeWall());

		r1.setSide(Direction.North, d0);
		r1.setSide(Direction.South, creator.makeWall());
		r1.setSide(Direction.East, creator.makeWall());
		r1.setSide(Direction.West, creator.makeWall());

		maze.addRoom(r0);
		maze.addRoom(r1);
		maze.setCurrentRoom(r0);

		return maze;
	}

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

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Choose maze type:");
		System.out.println("1. Basic maze");
		System.out.println("2. Red maze");
		System.out.println("3. Blue maze");
		System.out.print("Enter choice (1-3): ");

		int choice = scanner.nextInt();
		MazeGameCreator creator;

		switch (choice) {
			case 1:
				creator = new BasicMazeGameCreator();
				break;
			case 2:
				creator = new RedMazeGameCreator();
				break;
			case 3:
				creator = new BlueMazeGameCreator();
				break;
			default:
				creator = new BasicMazeGameCreator();
		}

		Maze maze = creator.loadMaze("large.maze");
		MazeViewer viewer = new MazeViewer(maze);
		viewer.run();
	}
}

