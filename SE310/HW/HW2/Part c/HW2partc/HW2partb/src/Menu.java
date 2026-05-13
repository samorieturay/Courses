public class Menu {
    private Output output;

    public Menu() {
        output = new Output();
    }

    public Menu(Output output) {
        this.output = output;
    }

    public void printMainMenu() {
        System.out.println("\n=== Survey/Test System ===");
        System.out.println("1) Create a new Survey");
        System.out.println("2) Create a new Test");
        System.out.println("3) Display an existing Survey");
        System.out.println("4) Display an existing Test");
        System.out.println("5) Load an existing Survey");
        System.out.println("6) Load an existing Test");
        System.out.println("7) Save the current Survey");
        System.out.println("8) Save the current Test");
        System.out.println("9) Take the current Survey");
        System.out.println("10) Take the current Test");
        System.out.println("11) Modify the current Survey");
        System.out.println("12) Modify the current Test");
        System.out.println("13) Grade a Test");
        System.out.println("14) View Test Results");
        System.out.println("15) View Statistics");
        System.out.println("16) Quit");
        System.out.print("Select an option: ");
    }

    public void printCreateMenu() {
        System.out.println("\n=== Create Survey/Test ===");
        System.out.println("1) Add a new T/F question");
        System.out.println("2) Add a new multiple-choice question");
        System.out.println("3) Add a new short answer question");
        System.out.println("4) Add a new essay question");
        System.out.println("5) Add a new date question");
        System.out.println("6) Add a new matching question");
        System.out.println("7) Return to previous menu");
        System.out.print("Select an option: ");
    }

    public void printTestDisplayMenu() {
        System.out.println("\n=== Display Test ===");
        System.out.println("1) Display test (without answers)");
        System.out.println("2) Display test with correct answers");
        System.out.println("3) Return to main menu");
        System.out.print("Select an option: ");
    }

    public void print(String message) {
        output.print(message);
    }

    public void addMenu() {
        // Additional menu functionality
    }

    public Output getOutput() { return output; }
    public void setOutput(Output output) { this.output = output; }
}
