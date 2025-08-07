import java.util.Scanner;

public class Input {
    private String inputData;
    private Scanner scanner;

    public Input() {
        scanner = new Scanner(System.in);
    }

    public void inputDriver() {
        // Main input processing method
    }

    public String processInput() {
        return scanner.nextLine();
    }

    public boolean validateInput(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    public int getIntInput(int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.print("Please enter a number between " + min + " and " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number between " + min + " and " + max + ": ");
            }
        }
    }

    public String getStringInput() {
        return scanner.nextLine().trim();
    }

    public String getInputData() { return inputData; }
    public void setInputData(String inputData) { this.inputData = inputData; }
}

