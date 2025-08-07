import java.util.Scanner;

public class EssayQuestion extends ShortAnswerQuestion {
    private int essayLimit = 1000;

    public EssayQuestion() {
        super();
        this.type = "Essay";
        this.essayLimit = 1000;
    }

    public EssayQuestion(String prompt, int id) {
        super(prompt, id);
        this.type = "Essay";
        this.essayLimit = 1000;
    }

    public EssayQuestion(String prompt, int id, int essayLimit) {
        super(prompt, id);
        this.type = "Essay";
        this.essayLimit = essayLimit;
    }

    @Override
    public void displayQuestion() {
        System.out.println(id + ") " + prompt);
        System.out.println("(Essay - limit: " + essayLimit + " characters)");
    }

    @Override
    public void editResponse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your essay response:");
        String response = scanner.nextLine();

        while (response.length() > essayLimit) {
            System.out.println("Essay too long. Limit is " + essayLimit + " characters.");
            System.out.println("Enter your essay response:");
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

        System.out.print("Current word limit: " + essayLimit);
        System.out.print("\nEnter new word limit (or press Enter to keep current): ");
        String limitStr = scanner.nextLine();
        if (!limitStr.trim().isEmpty()) {
            try {
                this.essayLimit = Integer.parseInt(limitStr);
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
            System.out.print("How many essay responses would you like to give? ");
            int numResponses = Integer.parseInt(scanner.nextLine());
            StringBuilder responses = new StringBuilder();

            for (int i = 0; i < numResponses; i++) {
                System.out.println("Essay " + (i + 1) + ":");
                String response = scanner.nextLine();
                while (response.length() > essayLimit) {
                    System.out.println("Essay too long. Limit is " + essayLimit + " characters.");
                    System.out.println("Essay " + (i + 1) + ":");
                    response = scanner.nextLine();
                }
                responses.append("Essay ").append(i + 1).append(": ").append(response).append("\n");
            }
            return responses.toString();
        } else {
            System.out.print("Your answer: ");
            String response = scanner.nextLine();
            while (response.length() > essayLimit) {
                System.out.println("Essay too long. Limit is " + essayLimit + " characters.");
                System.out.print("Your answer: ");
                response = scanner.nextLine();
            }
            return response;
        }
    }

    public int getWordLimit() { return essayLimit; }
    public void setWordLimit(int limit) { this.essayLimit = limit; }
}
