import java.util.Scanner;

public class ShortAnswerQuestion extends Question {
    protected int shortAnswerLimit = 100;

    public ShortAnswerQuestion() {
        super();
        this.type = "Short Answer";
    }

    public ShortAnswerQuestion(String prompt, int id) {
        super(prompt, "Short Answer", id);
    }

    @Override
    public void displayQuestion() {
        System.out.println(id + ") " + prompt);
        System.out.println("(Short answer - limit: " + shortAnswerLimit + " characters)");
    }

    @Override
    public void editResponse() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your short answer: ");
        String response = scanner.nextLine();

        while (response.length() > shortAnswerLimit) {
            System.out.println("Answer too long. Limit is " + shortAnswerLimit + " characters.");
            System.out.print("Enter your short answer: ");
            response = scanner.nextLine();
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

        System.out.print("Current character limit: " + shortAnswerLimit);
        System.out.print("\nEnter new character limit (or press Enter to keep current): ");
        String limitStr = scanner.nextLine();
        if (!limitStr.trim().isEmpty()) {
            try {
                this.shortAnswerLimit = Integer.parseInt(limitStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, keeping current limit.");
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
            System.out.print("How many responses would you like to give? ");
            int numResponses = Integer.parseInt(scanner.nextLine());
            StringBuilder responses = new StringBuilder();

            for (int i = 0; i < numResponses; i++) {
                System.out.print("Response " + (i + 1) + ": ");
                String response = scanner.nextLine();
                while (response.length() > shortAnswerLimit) {
                    System.out.println("Answer too long. Limit is " + shortAnswerLimit + " characters.");
                    System.out.print("Response " + (i + 1) + ": ");
                    response = scanner.nextLine();
                }
                responses.append(response).append(" | ");
            }
            return responses.toString();
        } else {
            System.out.print("Your answer: ");
            String response = scanner.nextLine();
            while (response.length() > shortAnswerLimit) {
                System.out.println("Answer too long. Limit is " + shortAnswerLimit + " characters.");
                System.out.print("Your answer: ");
                response = scanner.nextLine();
            }
            return response;
        }
    }

    public int getShortAnswerLimit() { return shortAnswerLimit; }
    public void setShortAnswerLimit(int limit) { this.shortAnswerLimit = limit; }
}
