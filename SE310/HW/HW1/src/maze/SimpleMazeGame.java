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
public class SimpleMazeGame
{
	/**
	 * Creates a small maze.
	 */
	public static Maze createMaze()
	{
		
		Maze maze = new Maze();

		// Room ID *must* start at 0
		Room r0 = new Room(0);
		Room r1 = new Room(1);

		// Doors need to be associated with rooms
		Door d0 = new Door(r0, r1);

		// Setup all sides of r0
		r0.setSide(Direction.North, new Wall());
		r0.setSide(Direction.South, d0);
		r0.setSide(Direction.East, new Wall());
		r0.setSide(Direction.West, new Wall());

		// Same for r1
		r1.setSide(Direction.North, d0);
		r1.setSide(Direction.South, new Wall());
		r1.setSide(Direction.East, new Wall());
		r1.setSide(Direction.West, new Wall());

		// Add both rooms to the maze
		maze.addRoom(r0);
		maze.addRoom(r1);

		// Must set current room to the get the ball
		maze.setCurrentRoom(r0);

		return maze;


	}

	public static Maze loadMaze(String filename) throws Exception
	{
		Maze maze = new Maze();

		// storing all the rooms and doors
		ArrayList<Room> allRooms = new ArrayList<Room>();
		ArrayList<Door> allDoors = new ArrayList<Door>();
		ArrayList<String> doorNames = new ArrayList<String>();

		// Read the file first time to make all the rooms
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();

		while (line != null) {
			String[] parts = line.split(" ");

			if (parts[0].equals("room")) {
				int roomNum = Integer.parseInt(parts[1]);
				Room r = new Room(roomNum);
				allRooms.add(r);
				maze.addRoom(r);
			}

			line = br.readLine();
		}
		br.close();

		// reading he file a second time to make all the doors
		FileReader fr2 = new FileReader(filename);
		BufferedReader br2 = new BufferedReader(fr2);
		line = br2.readLine();

		while (line != null) {
			String[] parts = line.split(" ");

			if (parts[0].equals("door")) {
				String doorName = parts[1];
				int room1Num = Integer.parseInt(parts[2]);
				int room2Num = Integer.parseInt(parts[3]);

				// Find the rooms
				Room room1 = null;
				Room room2 = null;
				for (int i = 0; i < allRooms.size(); i++) {
					if (allRooms.get(i).getNumber() == room1Num) {
						room1 = allRooms.get(i);
					}
					if (allRooms.get(i).getNumber() == room2Num) {
						room2 = allRooms.get(i);
					}
				}

				Door d = new Door(room1, room2);
				allDoors.add(d);
				doorNames.add(doorName);
			}

			line = br2.readLine();
		}
		br2.close();

		// reading the file a third time to set up all the room sides
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

				// get this room
				Room currentRoom = null;
				for (int i = 0; i < allRooms.size(); i++) {
					if (allRooms.get(i).getNumber() == roomNum) {
						currentRoom = allRooms.get(i);
					}
				}

				//north side
				if (north.equals("wall")) {
					currentRoom.setSide(Direction.North, new Wall());
				} else if (north.startsWith("d")) {
					// Find the door
					Door theDoor = null;
					for (int i = 0; i < doorNames.size(); i++) {
						if (doorNames.get(i).equals(north)) {
							theDoor = allDoors.get(i);
						}
					}
					currentRoom.setSide(Direction.North, theDoor);
				} else {
					// then it is a room number
					int otherRoomNum = Integer.parseInt(north);
					Room otherRoom = null;
					for (int i = 0; i < allRooms.size(); i++) {
						if (allRooms.get(i).getNumber() == otherRoomNum) {
							otherRoom = allRooms.get(i);
						}
					}
					currentRoom.setSide(Direction.North, otherRoom);
				}

				//south side
				if (south.equals("wall")) {
					currentRoom.setSide(Direction.South, new Wall());
				} else if (south.startsWith("d")) {
					Door theDoor = null;
					for (int i = 0; i < doorNames.size(); i++) {
						if (doorNames.get(i).equals(south)) {
							theDoor = allDoors.get(i);
						}
					}
					currentRoom.setSide(Direction.South, theDoor);
				} else {
					int otherRoomNum = Integer.parseInt(south);
					Room otherRoom = null;
					for (int i = 0; i < allRooms.size(); i++) {
						if (allRooms.get(i).getNumber() == otherRoomNum) {
							otherRoom = allRooms.get(i);
						}
					}
					currentRoom.setSide(Direction.South, otherRoom);
				}

				//east side
				if (east.equals("wall")) {
					currentRoom.setSide(Direction.East, new Wall());
				} else if (east.startsWith("d")) {
					Door theDoor = null;
					for (int i = 0; i < doorNames.size(); i++) {
						if (doorNames.get(i).equals(east)) {
							theDoor = allDoors.get(i);
						}
					}
					currentRoom.setSide(Direction.East, theDoor);
				} else {
					int otherRoomNum = Integer.parseInt(east);
					Room otherRoom = null;
					for (int i = 0; i < allRooms.size(); i++) {
						if (allRooms.get(i).getNumber() == otherRoomNum) {
							otherRoom = allRooms.get(i);
						}
					}
					currentRoom.setSide(Direction.East, otherRoom);
				}

				//west side
				if (west.equals("wall")) {
					currentRoom.setSide(Direction.West, new Wall());
				} else if (west.startsWith("d")) {
					Door theDoor = null;
					for (int i = 0; i < doorNames.size(); i++) {
						if (doorNames.get(i).equals(west)) {
							theDoor = allDoors.get(i);
						}
					}
					currentRoom.setSide(Direction.West, theDoor);
				} else {
					int otherRoomNum = Integer.parseInt(west);
					Room otherRoom = null;
					for (int i = 0; i < allRooms.size(); i++) {
						if (allRooms.get(i).getNumber() == otherRoomNum) {
							otherRoom = allRooms.get(i);
						}
					}
					currentRoom.setSide(Direction.West, otherRoom);
				}
			}

			line = br3.readLine();
		}
		br3.close();

		maze.setCurrentRoom(0);

		return maze;
	}

	public static void main(String[] args) throws Exception
	{
		Maze maze = loadMaze("large.maze");
		MazeViewer viewer = new MazeViewer(maze);
		viewer.run();
	}
}
