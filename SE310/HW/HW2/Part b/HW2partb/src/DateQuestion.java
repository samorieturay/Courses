import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DateQuestion extends Question {

    public DateQuestion() {
        super();
        this.type = "Date";
    }

    public DateQuestion(String prompt, int id) {
        super(prompt, "Date", id);
    }

    @Override
    public void displayQuestion() {
        System.out.println(id + ") " + prompt);
        System.out.println("A date should be entered in the following format: YYYY-MM-DD");
    }

    @Override
    public void editResponse() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();

        while (!isValidDate(dateStr)) {
            System.out.print("Invalid date format. Enter date (YYYY-MM-DD): ");
            dateStr = scanner.nextLine();
        }

        this.questionResponse = new SingularResponse(this.id, 1, dateStr);
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
            System.out.print("How many dates would you like to enter? ");
            int numDates = Integer.parseInt(scanner.nextLine());
            StringBuilder responses = new StringBuilder();

            for (int i = 0; i < numDates; i++) {
                System.out.print("Date " + (i + 1) + " (YYYY-MM-DD): ");
                String dateStr = scanner.nextLine();
                while (!isValidDate(dateStr)) {
                    System.out.print("Invalid date format. Date " + (i + 1) + " (YYYY-MM-DD): ");
                    dateStr = scanner.nextLine();
                }
                responses.append(dateStr).append(" ");
            }
            return responses.toString().trim();
        } else {
            System.out.print("Your answer: ");
            String dateStr = scanner.nextLine();
            while (!isValidDate(dateStr)) {
                System.out.print("Invalid date format. Enter date (YYYY-MM-DD): ");
                dateStr = scanner.nextLine();
            }
            return dateStr;
        }
    }

    private boolean isValidDate(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}

