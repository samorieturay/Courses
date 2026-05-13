import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultipleChoiceQuestion extends Question {
    protected List<String> choices;
    protected String multipleChoiceFormat;

    public MultipleChoiceQuestion() {
        super();
        this.type = "Multiple Choice";
        this.choices = new ArrayList<>();
    }

    public MultipleChoiceQuestion(String prompt, int id) {
        super(prompt, "Multiple Choice", id);
        this.choices = new ArrayList<>();
    }

    @Override
    public void displayQuestion() {
        System.out.println(id + ") " + prompt);
        for (int i = 0; i < choices.size(); i++) {
            char letter = (char) ('A' + i);
            System.out.print(letter + ") " + choices.get(i) + " ");
        }
        System.out.println();
    }

    @Override
    public void editResponse() {
        Scanner scanner = new Scanner(System.in);
        displayQuestion();
        System.out.print("Enter your choice (A, B, C, etc.): ");
        String response = scanner.nextLine().trim().toUpperCase();

        while (!isValidChoice(response)) {
            System.out.print("Invalid choice. Please enter a valid option: ");
            response = scanner.nextLine().trim().toUpperCase();
        }

        this.questionResponse = new SingularResponse(this.id, 1, response);
    }

    @Override
    public void editQuestion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Current prompt: " + prompt);
        System.out.print("Do you wish to modify the prompt? (y/n): ");
        String modifyPrompt = scanner.nextLine().toLowerCase();

        if (modifyPrompt.equals("y") || modifyPrompt.equals("yes")) {
            System.out.print("Enter new prompt: ");
            String newPrompt = scanner.nextLine();
            if (!newPrompt.trim().isEmpty()) {
                this.prompt = newPrompt;
            }
        }

        System.out.print("Do you wish to modify choices? (y/n): ");
        String modifyChoices = scanner.nextLine().toLowerCase();

        if (modifyChoices.equals("y") || modifyChoices.equals("yes")) {
            displayChoices();
            System.out.print("Which choice do you want to modify? (A, B, C, etc.): ");
            String choiceToModify = scanner.nextLine().trim().toUpperCase();

            if (isValidChoice(choiceToModify)) {
                int index = choiceToModify.charAt(0) - 'A';
                System.out.print("Enter new value for choice " + choiceToModify + ": ");
                String newValue = scanner.nextLine();
                if (!newValue.trim().isEmpty()) {
                    choices.set(index, newValue);
                }
            }
        }

        System.out.print("Allow multiple responses? (y/n): ");
        String multiResponse = scanner.nextLine().toLowerCase();
        this.allowMultipleResponses = multiResponse.equals("y") || multiResponse.equals("yes");
    }

    @Override
    public String takeQuestion() {
        Scanner scanner = new Scanner(System.in);
        displayQuestion();

        if (allowMultipleResponses) {
            System.out.print("How many choices would you like to select? ");
            int numChoices = Integer.parseInt(scanner.nextLine());
            StringBuilder responses = new StringBuilder();

            for (int i = 0; i < numChoices; i++) {
                System.out.print("Choice " + (i + 1) + ": ");
                String response = scanner.nextLine().trim().toUpperCase();
                while (!isValidChoice(response)) {
                    System.out.print("Invalid choice. Please enter a valid option: ");
                    response = scanner.nextLine().trim().toUpperCase();
                }
                responses.append(response).append(" ");
            }
            return responses.toString().trim();
        } else {
            System.out.print("Your answer: ");
            String response = scanner.nextLine().trim().toUpperCase();
            while (!isValidChoice(response)) {
                System.out.print("Invalid choice. Please enter a valid option: ");
                response = scanner.nextLine().trim().toUpperCase();
            }
            return response;
        }
    }

    private boolean isValidChoice(String choice) {
        if (choice.length() != 1) return false;
        char c = choice.charAt(0);
        return c >= 'A' && c < ('A' + choices.size());
    }

    private void displayChoices() {
        for (int i = 0; i < choices.size(); i++) {
            char letter = (char) ('A' + i);
            System.out.println(letter + ") " + choices.get(i));
        }
    }

    public List<String> getChoices() { return choices; }
    public void setChoices(List<String> choices) { this.choices = choices; }
    public void addChoice(String choice) { this.choices.add(choice); }
    public String getMultipleChoiceFormat() { return multipleChoiceFormat; }
    public void setMultipleChoiceFormat(String format) { this.multipleChoiceFormat = format; }
}
