public class Menu {
    private Output output;

    public Menu() {
        output = new Output();
    }

    public Menu(Output output) {
        this.output = output;
    }

    public void printMainMenu() {
        System.out.println("\n=== Survey System ===");
        System.out.println("1) Create a new Survey");
        System.out.println("2) Display an existing Survey");
        System.out.println("3) Load an existing Survey");
        System.out.println("4) Save the current Survey");
        System.out.println("5) Take the current Survey");
        System.out.println("6) Modify the current Survey");
        System.out.println("7) Quit");
        System.out.print("Select an option: ");
    }

    public void printCreateMenu() {
        System.out.println("\n=== Create Survey ===");
        System.out.println("1) Add a new T/F question");
        System.out.println("2) Add a new multiple-choice question");
        System.out.println("3) Add a new short answer question");
        System.out.println("4) Add a new essay question");
        System.out.println("5) Add a new date question");
        System.out.println("6) Add a new matching question");
        System.out.println("7) Return to previous menu");
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
