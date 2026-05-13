import java.util.List;
import java.util.Scanner;

public class SurveyTestController {
    private Survey currentSurvey;
    private Test currentTest;
    private Menu menu;
    private Input input;
    private Output output;
    private SerializationHandler serializationHandler;
    private Grader grader;
    private ResultsTabulator resultsTabulator;
    private int nextQuestionId = 1;

    public SurveyTestController() {
        menu = new Menu();
        input = new Input();
        output = new Output();
        serializationHandler = new SerializationHandler();
        grader = new Grader();
        resultsTabulator = new ResultsTabulator();
    }

    public void run() {
        boolean running = true;

        while (running) {
            try {
                menu.printMainMenu();
                int choice = input.getIntInput(1, 16);

                switch (choice) {
                    case 1: createSurvey(); break;
                    case 2: createTest(); break;
                    case 3: displaySurvey(); break;
                    case 4: displayTest(); break;
                    case 5: loadSurvey(); break;
                    case 6: loadTest(); break;
                    case 7: saveSurvey(); break;
                    case 8: saveTest(); break;
                    case 9: takeSurvey(); break;
                    case 10: takeTest(); break;
                    case 11: modifySurvey(); break;
                    case 12: modifyTest(); break;
                    case 13: gradeTest(); break;
                    case 14: viewTestResults(); break;
                    case 15: viewStatistics(); break;
                    case 16:
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

        createQuestions(false); // false = survey mode
    }

    private void createTest() {
        if (currentTest == null) {
            System.out.print("Enter test name: ");
            String testName = input.getStringInput();
            currentTest = new Test(testName, 1);
            nextQuestionId = 1;
        }

        createQuestions(true); // true = test mode
    }

    private void createQuestions(boolean isTestMode) {
        boolean creatingQuestions = true;

        while (creatingQuestions) {
            menu.printCreateMenu();
            int choice = input.getIntInput(1, 7);

            Question newQuestion = null;

            switch (choice) {
                case 1: newQuestion = createTrueFalseQuestion(); break;
                case 2: newQuestion = createMultipleChoiceQuestion(); break;
                case 3: newQuestion = createShortAnswerQuestion(); break;
                case 4: newQuestion = createEssayQuestion(); break;
                case 5: newQuestion = createDateQuestion(); break;
                case 6: newQuestion = createMatchingQuestion(); break;
                case 7: creatingQuestions = false; break;
            }

            if (newQuestion != null) {
                if (isTestMode) {
                    // Create test question with correct answer
                    TestQuestion testQuestion = new TestQuestion(newQuestion);

                    if (!(newQuestion instanceof EssayQuestion)) {
                        System.out.print("Enter the correct answer: ");
                        String correctAnswer = input.getStringInput();
                        testQuestion.setCorrectAnswer(correctAnswer);
                    }

                    currentTest.addTestQuestion(testQuestion);
                    System.out.println("Test question added successfully!");
                } else {
                    // Add to survey
                    currentSurvey.addQuestion(newQuestion);
                    System.out.println("Survey question added successfully!");
                }
            }
        }
    }

    private Question createTrueFalseQuestion() {
        System.out.print("Enter the prompt for your True/False question: ");
        String prompt = input.getStringInput();

        TrueFalseQuestion question = new TrueFalseQuestion(prompt, nextQuestionId++);

        System.out.print("Allow multiple responses? (y/n): ");
        String multiResponse = input.getStringInput().toLowerCase();
        question.setAllowMultipleResponses(multiResponse.equals("y") || multiResponse.equals("yes"));

        return question;
    }

    private Question createMultipleChoiceQuestion() {
        System.out.print("Enter the prompt for your multiple-choice question: ");
        String prompt = input.getStringInput();

        System.out.print("Enter the number of choices: ");
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

        return question;
    }

    private Question createShortAnswerQuestion() {
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

        return question;
    }

    private Question createEssayQuestion() {
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

        return question;
    }

    private Question createDateQuestion() {
        System.out.print("Enter the prompt for your date question: ");
        String prompt = input.getStringInput();

        DateQuestion question = new DateQuestion(prompt, nextQuestionId++);

        System.out.print("Allow multiple responses? (y/n): ");
        String multiResponse = input.getStringInput().toLowerCase();
        question.setAllowMultipleResponses(multiResponse.equals("y") || multiResponse.equals("yes"));

        return question;
    }

    private Question createMatchingQuestion() {
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

        return question;
    }

    private void displaySurvey() {
        if (currentSurvey == null) {
            System.out.println("You must have a survey loaded in order to display it.");
            return;
        }
        currentSurvey.printSurvey();
    }

    private void displayTest() {
        if (currentTest == null) {
            System.out.println("You must have a test loaded in order to display it.");
            return;
        }

        menu.printTestDisplayMenu();
        int choice = input.getIntInput(1, 3);

        switch (choice) {
            case 1:
                currentTest.printTest();
                break;
            case 2:
                currentTest.printTestWithAnswers();
                break;
            case 3:
                // Return to main menu
                break;
        }
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

    private void loadTest() {
        List<String> availableTests = serializationHandler.getAvailableTests();

        if (availableTests.isEmpty()) {
            System.out.println("No tests available to load.");
            return;
        }

        System.out.println("Please select a test to load:");
        for (int i = 0; i < availableTests.size(); i++) {
            System.out.println((i + 1) + ") " + availableTests.get(i));
        }

        System.out.print("Enter your choice: ");
        int choice = input.getIntInput(1, availableTests.size());
        String selectedFile = availableTests.get(choice - 1);

        Test loadedTest = serializationHandler.loadTest(selectedFile);
        if (loadedTest != null) {
            currentTest = loadedTest;
            nextQuestionId = currentTest.getTestQuestions().size() + 1;
            System.out.println("Test loaded successfully: " + currentTest.getTestName());
        } else {
            System.out.println("Failed to load test.");
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

    private void saveTest() {
        if (currentTest == null) {
            System.out.println("You must have a test loaded in order to save it.");
            return;
        }

        serializationHandler.saveTest(currentTest);
        System.out.println("Test saved successfully!");
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
            System.out.println();
        }

        serializationHandler.saveSurveyResponse(surveyResponse);
        System.out.println("Thank you for taking the survey! Your responses have been saved.");
    }

    private void takeTest() {
        if (currentTest == null) {
            System.out.println("You must have a test loaded in order to take it.");
            return;
        }

        System.out.print("Enter your name: ");
        String respondentName = input.getStringInput();

        TestResponse testResponse = new TestResponse(currentTest, respondentName);

        System.out.println("\n=== Taking Test: " + currentTest.getTestName() + " ===");

        for (TestQuestion testQuestion : currentTest.getTestQuestions()) {
            String response = testQuestion.takeQuestion();
            SingularResponse singularResponse = new SingularResponse(testQuestion.getBaseQuestion().getId(), 1, response);
            testResponse.addResponse(singularResponse);
            System.out.println();
        }

        serializationHandler.saveTestResponse(testResponse);

        // Automatically grade the test
        TestResult result = grader.gradeTest(currentTest, testResponse);
        serializationHandler.saveTestResult(result);

        System.out.println("Thank you for taking the test! Your responses have been saved and graded.");
        System.out.println(result.generateReport());
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

    private void modifyTest() {
        if (currentTest == null) {
            System.out.println("You must have a test loaded in order to modify it.");
            return;
        }

        if (currentTest.getTestQuestions().isEmpty()) {
            System.out.println("No questions in the current test to modify.");
            return;
        }

        System.out.println("Current test questions:");
        for (int i = 0; i < currentTest.getTestQuestions().size(); i++) {
            TestQuestion tq = currentTest.getTestQuestions().get(i);
            System.out.println((i + 1) + ") " + tq.getBaseQuestion().getPrompt() +
                    " (" + tq.getBaseQuestion().getType() + ") - Answer: " + tq.getCorrectAnswer());
        }

        System.out.print("Which question do you wish to modify (enter number): ");
        int questionNum = input.getIntInput(1, currentTest.getTestQuestions().size());

        TestQuestion testQuestionToModify = currentTest.getTestQuestion(questionNum - 1);
        if (testQuestionToModify != null) {
            // Modify the base question
            testQuestionToModify.editQuestion();

            // Modify the correct answer if not an essay
            if (!(testQuestionToModify.getBaseQuestion() instanceof EssayQuestion)) {
                System.out.print("Current correct answer: " + testQuestionToModify.getCorrectAnswer());
                System.out.print("\nEnter new correct answer (or press Enter to keep current): ");
                String newAnswer = input.getStringInput();
                if (!newAnswer.trim().isEmpty()) {
                    testQuestionToModify.setCorrectAnswer(newAnswer);
                }
            }

            System.out.println("Test question modified successfully!");
        }
    }

    private void gradeTest() {
        // This method would load test responses and grade them
        System.out.println("Grade Test functionality - would load and grade test responses.");
        System.out.println("(Tests are automatically graded when taken)");
    }

    private void viewTestResults() {
        List<TestResult> results = serializationHandler.loadAllTestResults();

        if (results.isEmpty()) {
            System.out.println("No test results available.");
            return;
        }

        System.out.println("Available test results:");
        for (int i = 0; i < results.size(); i++) {
            TestResult result = results.get(i);
            System.out.println((i + 1) + ") " + result.getTest().getTestName() +
                    " - " + result.getTestResponse().getRespondentName() +
                    " - Score: " + String.format("%.2f", result.getScorePercentage()) + "%");
        }

        System.out.print("Select a result to view details (or 0 to return): ");
        int choice = input.getIntInput(0, results.size());

        if (choice > 0) {
            TestResult selectedResult = results.get(choice - 1);
            grader.displayDetailedResults(selectedResult);
        }
    }

    private void viewStatistics() {
        List<TestResult> testResults = serializationHandler.loadAllTestResults();

        if (testResults.isEmpty()) {
            System.out.println("No test results available for statistics.");
            return;
        }

        TestStats stats = resultsTabulator.tabulateTestResults(testResults);
        System.out.println(stats.generateSummary());
    }

    public static void main(String[] args) {
        SurveyTestController controller = new SurveyTestController();
        controller.run();
    }
}
