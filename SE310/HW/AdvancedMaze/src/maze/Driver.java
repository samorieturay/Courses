package maze;

import maze.ui.MazeViewer;
import java.util.Scanner;

public class Driver {

    public static Maze loadMaze(String path, MazeFactory factory) throws Exception {
        return factory.loadMaze(path);
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose maze type:");
        System.out.println("1. Red maze (Pink rooms, Red walls)");
        System.out.println("2. Blue maze (Green rooms, Blue walls, Brown doors)");
        System.out.print("Enter choice (1-2): ");

        int choice = scanner.nextInt();
        MazeFactory factory;

        switch (choice) {
            case 1:
                factory = new RedMazeFactory();
                break;
            case 2:
                factory = new BlueMazeFactory();
                break;
            default:
                factory = new RedMazeFactory();
        }

        try {
            Maze maze = loadMaze("large.maze", factory);
            MazeViewer viewer = new MazeViewer(maze);
            viewer.run();
        } catch (Exception e) {
            System.out.println("Could not load large.maze, trying small.maze...");
            try {
                Maze maze = loadMaze("small.maze", factory);
                MazeViewer viewer = new MazeViewer(maze);
                viewer.run();
            } catch (Exception e2) {
                System.out.println("Could not load either maze file. Please check file availability.");
                e2.printStackTrace();
            }
        }

        scanner.close();
    }
}