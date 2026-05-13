public class Menu {
    private Output output;

    public Menu() {
        output = new Output();
    }

    public Menu(Output output) {
        this.output = output;
    }

    // Main Menu 1 - Survey or Test selection
    public void printMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1) Survey");
        System.out.println("2) Test");
        System.out.print("Select an option: ");
    }

    // Survey Menu 2
    public void printSurveyMenu() {
        System.out.println("\n=== Survey Menu ===");
        System.out.println("1) Create a new Survey");
        System.out.println("2) Display an existing Survey");
        System.out.println("3) Load an existing Survey");
        System.out.println("4) Save the current Survey");
        System.out.println("5) Take the current Survey");
        System.out.println("6) Modify the current Survey");
        System.out.println("7) Tabulate a survey");
        System.out.println("8) Return to previous menu");
        System.out.print("Select an option: ");
    }

    // Test Menu 2
    public void printTestMenu() {
        System.out.println("\n=== Test Menu ===");
        System.out.println("1) Create a new Test");
        System.out.println("2) Display an existing Test without correct answers");
        System.out.println("3) Display an existing Test with correct answers");
        System.out.println("4) Load an existing Test");
        System.out.println("5) Save the current Test");
        System.out.println("6) Take the current Test");
        System.out.println("7) Modify the current Test");
        System.out.println("8) Tabulate a Test");
        System.out.println("9) Grade a Test");
        System.out.println("10) Return to the previous menu");
        System.out.print("Select an option: ");
    }

    // Menu 3 - Create Test/Survey Questions
    public void printCreateMenu() {
        System.out.println("\n=== Add Questions ===");
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

    public Output getOutput() { return output; }
    public void setOutput(Output output) { this.output = output; }
}