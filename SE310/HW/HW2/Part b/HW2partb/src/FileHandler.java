import java.io.*;
import java.nio.file.*;
import java.util.List;

public class FileHandler {

    public void newFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + fileName);
            } else {
                System.out.println("File already exists: " + fileName);
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }

    public void createDirectory(String dirName) {
        try {
            Files.createDirectories(Paths.get(dirName));
        } catch (IOException e) {
            System.err.println("Error creating directory: " + e.getMessage());
        }
    }

    public List<String> readLines(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    public void appendToFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(content + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error appending to file: " + e.getMessage());
        }
    }

    public void writeToFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
