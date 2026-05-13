import java.util.List;
import java.util.Scanner;

public class SurveyAppController {
    private Survey currentSurvey;
    private Menu menu;
    private Input input;
    private Output output;
    private SerializationHandler serializationHandler;
    private int nextQuestionId = 1;

    public SurveyAppController() {
        menu = new Menu();
        input = new Input();
        output = new Output();
        serializationHandler = new SerializationHandler();
    }

    public void run() {
        boolean running = true;

        while (running) {
            try {
                menu.printMainMenu();
                int choice = input.getIntInput(1, 7);

                switch (choice) {
                    case 1:
                        createSurvey();
                        break;
                    case 2:
                        displaySurvey();
                        break;
                    case 3:
                        loadSurvey();
                        break;
                    case 4:
                        saveSurvey();
                        break;
                    case 5:
                        takeSurvey();
                        break;
                    case 6:
                        modifySurvey();
                        break;
                    case 7:
                        System.out.println("Goodbye!");
                        running = false;
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
                e.printStackTrace();
            }
        }
    }

    private void createSurvey() {
        if (currentSurvey == null) {
            System.out.print("Enter survey name: ");
            String surveyName = input.getStringInput();
            currentSurvey = new Survey(surveyName, 1);
            nextQuestionId = 1;
        }

        boolean creatingQuestions = true;
        while (creatingQuestions) {
            menu.printCreateMenu();
            int choice = input.getIntInput(1, 7);

            switch (choice) {
                case 1:
                    createTrueFalseQuestion();
                    break;
                case 2:
                    createMultipleChoiceQuestion();
                    break;
                case 3:
                    createShortAnswerQuestion();
                    break;
                case 4:
                    createEssayQuestion();
                    break;
                case 5:
                    createDateQuestion();
                    break;
                case 6:
                    createMatchingQuestion();
                    break;
                case 7:
                    creatingQuestions = false;
                    break;
            }
        }
    }

    private void createTrueFalseQuestion() {
        System.out.print("Enter the prompt for your True/False question: ");
        String prompt = input.getStringInput();

        TrueFalseQuestion question = new TrueFalseQuestion(prompt, nextQuestionId++);

        System.out.print("Allow multiple responses? (y/n): ");
        String multiResponse = input.getStringInput().toLowerCase();
        question.setAllowMultipleResponses(multiResponse.equals("y") || multiResponse.equals("yes"));

        currentSurvey.addQuestion(question);
        System.out.println("True/False question added successfully!");
    }

    private void createMultipleChoiceQuestion() {
        System.out.print("Enter the prompt for your multiple-choice question: ");
        String prompt = input.getStringInput();

        System.out.print("Enter the number of choices for your multiple-choice question: ");
        int numChoices = input.getIntInput(2, 10);

        MultipleChoiceQuestion question = new MultipleChoiceQuestion(prompt, nextQuestionId++);

        for (int i = 1; i <= numChoices; i++) {
            System.out.print("Enter choice #" + i + ": ");
            String choice = input.getStringInput();
            question.addChoice(choice);
        }

        System.out.print("Allow multiple responses? (y/n): ");
        String multiResponse = input.getStringInput().toLowerCase();
        question.setAllowMultipleResponses(multiResponse.equals("y") || multiResponse.equals("yes"));

        currentSurvey.addQuestion(question);
        System.out.println("Multiple choice question added successfully!");
    }

    private void createShortAnswerQuestion() {
        System.out.print("Enter the prompt for your short answer question: ");
        String prompt = input.getStringInput();

        ShortAnswerQuestion question = new ShortAnswerQuestion(prompt, nextQuestionId++);

        System.out.print("Enter character limit (default 100): ");
        String limitStr = input.getStringInput();
        if (!limitStr.isEmpty()) {
            try {
                int limit = Integer.parseInt(limitStr);
                question.setShortAnswerLimit(limit);
            } catch (NumberFormatException e) {
                System.out.println("Invalid limit, using default (100)");
            }
        }

        System.out.print("Allow multiple responses? (y/n): ");
        String multiResponse = input.getStringInput().toLowerCase();
        question.setAllowMultipleResponses(multiResponse.equals("y") || multiResponse.equals("yes"));

        currentSurvey.addQuestion(question);
        System.out.println("Short answer question added successfully!");
    }

    private void createEssayQuestion() {
        System.out.print("Enter the prompt for your essay question: ");
        String prompt = input.getStringInput();

        EssayQuestion question = new EssayQuestion(prompt, nextQuestionId++);

        System.out.print("Enter character limit (default 1000): ");
        String limitStr = input.getStringInput();
        if (!limitStr.isEmpty()) {
            try {
                int limit = Integer.parseInt(limitStr);
                question.setWordLimit(limit);
            } catch (NumberFormatException e) {
                System.out.println("Invalid limit, using default (1000)");
            }
        }

        System.out.print("Allow multiple responses? (y/n): ");
        String multiResponse = input.getStringInput().toLowerCase();
        question.setAllowMultipleResponses(multiResponse.equals("y") || multiResponse.equals("yes"));

        currentSurvey.addQuestion(question);
        System.out.println("Essay question added successfully!");
    }

    private void createDateQuestion() {
        System.out.print("Enter the prompt for your date question: ");
        String prompt = input.getStringInput();

        DateQuestion question = new DateQuestion(prompt, nextQuestionId++);

        System.out.print("Allow multiple responses? (y/n): ");
        String multiResponse = input.getStringInput().toLowerCase();
        question.setAllowMultipleResponses(multiResponse.equals("y") || multiResponse.equals("yes"));

        currentSurvey.addQuestion(question);
        System.out.println("Date question added successfully!");
    }

    private void createMatchingQuestion() {
        System.out.print("Enter the prompt for your matching question: ");
        String prompt = input.getStringInput();

        MatchingQuestion question = new MatchingQuestion(prompt, nextQuestionId++);

        System.out.print("Enter the number of matching pairs: ");
        int numPairs = input.getIntInput(2, 10);

        for (int i = 1; i <= numPairs; i++) {
            System.out.print("Enter left item #" + i + ": ");
            String leftItem = input.getStringInput();
            System.out.print("Enter right item #" + i + ": ");
            String rightItem = input.getStringInput();
            question.addMatchingPair(leftItem, rightItem);
        }

        System.out.print("Allow multiple responses? (y/n): ");
        String multiResponse = input.getStringInput().toLowerCase();
        question.setAllowMultipleResponses(multiResponse.equals("y") || multiResponse.equals("yes"));

        currentSurvey.addQuestion(question);
        System.out.println("Matching question added successfully!");
    }

    private void displaySurvey() {
        if (currentSurvey == null) {
            System.out.println("You must have a survey loaded in order to display it.");
            return;
        }
        currentSurvey.printSurvey();
    }

    private void loadSurvey() {
        List<String> availableSurveys = serializationHandler.getAvailableSurveys();

        if (availableSurveys.isEmpty()) {
            System.out.println("No surveys available to load.");
            return;
        }

        System.out.println("Please select a file to load:");
        for (int i = 0; i < availableSurveys.size(); i++) {
            System.out.println((i + 1) + ") " + availableSurveys.get(i));
        }

        System.out.print("Enter your choice: ");
        int choice = input.getIntInput(1, availableSurveys.size());
        String selectedFile = availableSurveys.get(choice - 1);

        Survey loadedSurvey = serializationHandler.loadSurvey(selectedFile);
        if (loadedSurvey != null) {
            currentSurvey = loadedSurvey;
            nextQuestionId = currentSurvey.getQuestions().size() + 1;
            System.out.println("Survey loaded successfully: " + currentSurvey.getSurveyName());
        } else {
            System.out.println("Failed to load survey.");
        }
    }

    private void saveSurvey() {
        if (currentSurvey == null) {
            System.out.println("You must have a survey loaded in order to save it.");
            return;
        }

        serializationHandler.saveSurvey(currentSurvey);
        System.out.println("Survey saved successfully!");
    }

    private void takeSurvey() {
        if (currentSurvey == null) {
            System.out.println("You must have a survey loaded in order to take it.");
            return;
        }

        System.out.print("Enter your name: ");
        String respondentName = input.getStringInput();

        SurveyResponse surveyResponse = new SurveyResponse(currentSurvey, respondentName);

        System.out.println("\n=== Taking Survey: " + currentSurvey.getSurveyName() + " ===");

        for (Question question : currentSurvey.getQuestions()) {
            String response = question.takeQuestion();
            SingularResponse singularResponse = new SingularResponse(question.getId(), 1, response);
            surveyResponse.addResponse(singularResponse);
            System.out.println(); // Add spacing between questions
        }

        serializationHandler.saveSurveyResponse(surveyResponse);
        System.out.println("Thank you for taking the survey! Your responses have been saved.");
    }

    private void modifySurvey() {
        if (currentSurvey == null) {
            System.out.println("You must have a survey loaded in order to modify it.");
            return;
        }

        if (currentSurvey.getQuestions().isEmpty()) {
            System.out.println("No questions in the current survey to modify.");
            return;
        }

        System.out.println("Current questions:");
        for (int i = 0; i < currentSurvey.getQuestions().size(); i++) {
            Question q = currentSurvey.getQuestions().get(i);
            System.out.println((i + 1) + ") " + q.getPrompt() + " (" + q.getType() + ")");
        }

        System.out.print("Which question do you wish to modify (enter number): ");
        int questionNum = input.getIntInput(1, currentSurvey.getQuestions().size());

        Question questionToModify = currentSurvey.getQuestion(questionNum - 1);
        if (questionToModify != null) {
            questionToModify.editQuestion();
            System.out.println("Question modified successfully!");
        }
    }

    public static void main(String[] args) {
        SurveyAppController controller = new SurveyAppController();
        controller.run();
    }
}
