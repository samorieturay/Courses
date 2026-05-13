import java.util.Scanner;

public class TrueFalseQuestion extends Question {
    private int options = 2; // True/False has 2 options

    public TrueFalseQuestion() {
        super();
        this.type = "True/False";
    }

    public TrueFalseQuestion(String prompt, int id) {
        super(prompt, "True/False", id);
    }

    @Override
    public void displayQuestion() {
        System.out.println(id + ") " + prompt);
        System.out.println("T/F");
    }

    @Override
    public void editResponse() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter T for True or F for False: ");
        String response = scanner.nextLine().trim().toUpperCase();
        while (!response.equals("T") && !response.equals("F")) {
            System.out.print("Invalid input. Enter T for True or F for False: ");
            response = scanner.nextLine().trim().toUpperCase();
        }
        this.questionResponse = new SingularResponse(this.id, 1, response);
    }

    @Override
    public void editQuestion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Current prompt: " + prompt);
        System.out.print("Enter new prompt (or press Enter to keep current): ");
        String newPrompt = scanner.nextLine();
        if (!newPrompt.trim().isEmpty()) {
            this.prompt = newPrompt;
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
            System.out.print("How many responses would you like to give? ");
            int numResponses = Integer.parseInt(scanner.nextLine());
            StringBuilder responses = new StringBuilder();

            for (int i = 0; i < numResponses; i++) {
                System.out.print("Response " + (i + 1) + ": ");
                String response = scanner.nextLine().trim().toUpperCase();
                while (!response.equals("T") && !response.equals("F")) {
                    System.out.print("Invalid input. Enter T for True or F for False: ");
                    response = scanner.nextLine().trim().toUpperCase();
                }
                responses.append(response).append(" ");
            }
            return responses.toString().trim();
        } else {
            System.out.print("Your answer: ");
            String response = scanner.nextLine().trim().toUpperCase();
            while (!response.equals("T") && !response.equals("F")) {
                System.out.print("Invalid input. Enter T for True or F for False: ");
                response = scanner.nextLine().trim().toUpperCase();
            }
            return response;
        }
    }

    public int getOptions() { return options; }
    public void setOptions(int options) { this.options = options; }
}
