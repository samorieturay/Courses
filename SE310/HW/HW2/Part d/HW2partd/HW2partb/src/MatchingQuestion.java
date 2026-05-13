import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MatchingQuestion extends Question {
    private Map<String, String> questionTable;
    private List<String> leftColumn;
    private List<String> rightColumn;

    public MatchingQuestion() {
        super();
        this.type = "Matching";
        this.questionTable = new HashMap<>();
        this.leftColumn = new ArrayList<>();
        this.rightColumn = new ArrayList<>();
    }

    public MatchingQuestion(String prompt, int id) {
        super(prompt, "Matching", id);
        this.questionTable = new HashMap<>();
        this.leftColumn = new ArrayList<>();
        this.rightColumn = new ArrayList<>();
    }

    @Override
    public void displayQuestion() {
        System.out.println(id + ") " + prompt);
        System.out.println("Match the items:");

        for (int i = 0; i < leftColumn.size(); i++) {
            char letter = (char) ('A' + i);
            System.out.printf("%-2s) %-20s", letter, leftColumn.get(i));

            if (i < rightColumn.size()) {
                System.out.printf("%d) %s", (i + 1), rightColumn.get(i));
            }
            System.out.println();
        }
    }

    @Override
    public void editResponse() {
        Scanner scanner = new Scanner(System.in);
        displayQuestion();
        Map<String, String> responses = new HashMap<>();

        for (int i = 0; i < leftColumn.size(); i++) {
            char letter = (char) ('A' + i);
            System.out.print("Match " + letter + " with number: ");
            String match = scanner.nextLine();
            responses.put(String.valueOf(letter), match);
        }

        MultiResponse multiResponse = new MultiResponse(this.id, 1);
        multiResponse.setResponseTable(responses);
        this.questionResponse = multiResponse;
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

        System.out.print("Do you want to modify the matching items? (y/n): ");
        String modify = scanner.nextLine().toLowerCase();

        if (modify.equals("y") || modify.equals("yes")) {
            System.out.print("Enter number of matching pairs: ");
            int numPairs = Integer.parseInt(scanner.nextLine());

            leftColumn.clear();
            rightColumn.clear();

            for (int i = 0; i < numPairs; i++) {
                System.out.print("Enter left item " + (i + 1) + ": ");
                leftColumn.add(scanner.nextLine());
                System.out.print("Enter right item " + (i + 1) + ": ");
                rightColumn.add(scanner.nextLine());
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
        StringBuilder responses = new StringBuilder();

        for (int i = 0; i < leftColumn.size(); i++) {
            char letter = (char) ('A' + i);
            System.out.print("Match " + letter + " with number: ");
            String match = scanner.nextLine();
            responses.append(letter).append(" ").append(match).append("\n");
        }

        return responses.toString();
    }

    public Map<String, String> getQuestionTable() { return questionTable; }
    public void setQuestionTable(Map<String, String> questionTable) { this.questionTable = questionTable; }
    public List<String> getLeftColumn() { return leftColumn; }
    public void setLeftColumn(List<String> leftColumn) { this.leftColumn = leftColumn; }
    public List<String> getRightColumn() { return rightColumn; }
    public void setRightColumn(List<String> rightColumn) { this.rightColumn = rightColumn; }
    public void addMatchingPair(String left, String right) {
        leftColumn.add(left);
        rightColumn.add(right);
    }
}
