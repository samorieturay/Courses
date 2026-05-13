import java.io.File;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;

public class SurveyTestController {
    private Survey currentSurvey;
    private Test currentTest;
    private Menu menu;
    private Input input;
    private Output output;
    private SerializationHandler serializationHandler;
    private Grader grader;
    private SurveyTestTabulator tabulator;
    private int nextQuestionId = 1;

    public SurveyTestController() {
        menu = new Menu();
        input = new Input();
        output = new Output();
        serializationHandler = new SerializationHandler();
        grader = new Grader();
        tabulator = new SurveyTestTabulator();
    }

    public void run() {
        boolean running = true;

        while (running) {
            try {
                menu.printMainMenu();
                int choice = input.getIntInput(1, 2);

                switch (choice) {
                    case 1:
                        runSurveyMenu();
                        break;
                    case 2:
                        runTestMenu();
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
                e.printStackTrace();
            }
        }
    }

    private void runSurveyMenu() {
        boolean inSurveyMenu = true;

        while (inSurveyMenu) {
            try {
                menu.printSurveyMenu();
                int choice = input.getIntInput(1, 8);

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
                        tabulateSurvey();
                        break;
                    case 8:
                        inSurveyMenu = false;
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
            }
        }
    }

    private void runTestMenu() {
        boolean inTestMenu = true;

        while (inTestMenu) {
            try {
                menu.printTestMenu();
                int choice = input.getIntInput(1, 10);

                switch (choice) {
                    case 1:
                        createTest();
                        break;
                    case 2:
                        displayTest(false);
                        break;
                    case 3:
                        displayTest(true);
                        break;
                    case 4:
                        loadTest();
                        break;
                    case 5:
                        saveTest();
                        break;
                    case 6:
                        takeTest();
                        break;
                    case 7:
                        modifyTest();
                        break;
                    case 8:
                        tabulateTest();
                        break;
                    case 9:
                        gradeTest();
                        break;
                    case 10:
                        inTestMenu = false;
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
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
                case 1:
                    newQuestion = createTrueFalseQuestion();
                    break;
                case 2:
                    newQuestion = createMultipleChoiceQuestion();
                    break;
                case 3:
                    newQuestion = createShortAnswerQuestion();
                    break;
                case 4:
                    newQuestion = createEssayQuestion();
                    break;
                case 5:
                    newQuestion = createDateQuestion();
                    break;
                case 6:
                    newQuestion = createMatchingQuestion();
                    break;
                case 7:
                    creatingQuestions = false;
                    break;
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

    private void displayTest(boolean showAnswers) {
        if (currentTest == null) {
            System.out.println("You must have a test loaded in order to display it.");
            return;
        }

        if (showAnswers) {
            currentTest.printTestWithAnswers();
        } else {
            currentTest.printTest();
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

        System.out.println("Please select a file to load:");
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
        System.out.println("Thank you for taking the test! Your responses have been saved.");
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

        System.out.print("What question do you wish to modify? ");
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
                    " (" + tq.getBaseQuestion().getType() + ")");
        }

        System.out.print("What question do you wish to modify? ");
        int questionNum = input.getIntInput(1, currentTest.getTestQuestions().size());

        TestQuestion testQuestionToModify = currentTest.getTestQuestion(questionNum - 1);
        if (testQuestionToModify != null) {
            // Display the current question for context
            System.out.println("\nCurrent question:");
            testQuestionToModify.displayQuestionWithAnswer();

            // Check if it's multiple choice for special handling
            if (testQuestionToModify.getBaseQuestion() instanceof MultipleChoiceQuestion) {
                modifyMultipleChoiceTestQuestion((MultipleChoiceQuestion) testQuestionToModify.getBaseQuestion(), testQuestionToModify);
            } else {
                // For other question types, modify the base question
                testQuestionToModify.editQuestion();

                // Modify the correct answer if not an essay
                if (!(testQuestionToModify.getBaseQuestion() instanceof EssayQuestion)) {
                    System.out.print("Current correct answer: " + testQuestionToModify.getCorrectAnswer());
                    System.out.print("\nDo you wish to modify the correct answer? (y/n): ");
                    String modifyAnswer = input.getStringInput().toLowerCase();

                    if (modifyAnswer.equals("y") || modifyAnswer.equals("yes")) {
                        System.out.print("Enter new correct answer: ");
                        String newAnswer = input.getStringInput();
                        testQuestionToModify.setCorrectAnswer(newAnswer);
                    }
                }
            }

            System.out.println("Test question modified successfully!");
        }
    }

    private void modifyMultipleChoiceTestQuestion(MultipleChoiceQuestion mcq, TestQuestion testQuestion) {
        System.out.print("Do you wish to modify the prompt? (y/n): ");
        String modifyPrompt = input.getStringInput().toLowerCase();

        if (modifyPrompt.equals("y") || modifyPrompt.equals("yes")) {
            System.out.print("Enter new prompt: ");
            String newPrompt = input.getStringInput();
            if (!newPrompt.trim().isEmpty()) {
                mcq.setPrompt(newPrompt);
            }
        }

        System.out.print("Do you wish to modify choices? (y/n): ");
        String modifyChoices = input.getStringInput().toLowerCase();

        if (modifyChoices.equals("y") || modifyChoices.equals("yes")) {
            // Display current choices
            for (int i = 0; i < mcq.getChoices().size(); i++) {
                char letter = (char) ('A' + i);
                System.out.println(letter + ") " + mcq.getChoices().get(i));
            }

            System.out.print("Which choice do you want to modify? ");
            String choiceToModify = input.getStringInput().trim().toUpperCase();

            if (choiceToModify.length() == 1) {
                char c = choiceToModify.charAt(0);
                if (c >= 'A' && c < ('A' + mcq.getChoices().size())) {
                    int index = c - 'A';
                    System.out.print("Enter new value for choice " + choiceToModify + ": ");
                    String newValue = input.getStringInput();
                    if (!newValue.trim().isEmpty()) {
                        mcq.getChoices().set(index, newValue);
                    }
                }
            }
        }

        // Modify correct answer
        System.out.print("Current correct answer: " + testQuestion.getCorrectAnswer());
        System.out.print("\nDo you wish to modify the correct answer? (y/n): ");
        String modifyAnswer = input.getStringInput().toLowerCase();

        if (modifyAnswer.equals("y") || modifyAnswer.equals("yes")) {
            System.out.print("Enter correct choice: ");
            String newAnswer = input.getStringInput().trim().toUpperCase();
            testQuestion.setCorrectAnswer(newAnswer);
        }
    }

    private void tabulateSurvey() {
        if (currentSurvey == null) {
            System.out.println("You must have a survey loaded in order to tabulate it.");
            return;
        }

        tabulator.tabulateSurvey(currentSurvey);
    }

    private void tabulateTest() {
        if (currentTest == null) {
            System.out.println("You must have a test loaded in order to tabulate it.");
            return;
        }

        tabulator.tabulateTest(currentTest);
    }

    private void gradeTest() {
        // Get available tests
        List<String> availableTests = serializationHandler.getAvailableTests();

        if (availableTests.isEmpty()) {
            System.out.println("No tests available to grade.");
            return;
        }

        // Display tests to select from
        System.out.println("Select an existing test to grade:");
        for (int i = 0; i < availableTests.size(); i++) {
            System.out.println((i + 1) + ") " + availableTests.get(i));
        }

        System.out.print("Enter your choice: ");
        int testChoice = input.getIntInput(1, availableTests.size());
        String selectedTestFile = availableTests.get(testChoice - 1);

        // Load the selected test
        Test testToGrade = serializationHandler.loadTest(selectedTestFile);
        if (testToGrade == null) {
            System.out.println("Failed to load test.");
            return;
        }

        // Get available test responses for this test
        List<String> responseFiles = getTestResponseFiles(testToGrade.getTestName());

        if (responseFiles.isEmpty()) {
            System.out.println("No response sets found for this test.");
            return;
        }

        // Display response sets
        System.out.println("Select an existing response set:");
        for (int i = 0; i < responseFiles.size(); i++) {
            System.out.println((i + 1) + ") " + formatResponseFileName(responseFiles.get(i)));
        }

        System.out.print("Enter your choice: ");
        int responseChoice = input.getIntInput(1, responseFiles.size());
        String selectedResponseFile = responseFiles.get(responseChoice - 1);

        // Load and grade the test response
        TestResponse testResponse = loadTestResponse(selectedResponseFile);
        if (testResponse != null) {
            TestResult result = grader.gradeTest(testToGrade, testResponse);

            // Display result in the required format
            int totalQuestions = result.getTotalQuestions();
            int gradedQuestions = result.getGradedQuestions();
            int correctAnswers = result.getCorrectAnswers();
            int ungradedEssays = result.getUngradedEssays();

            double scorePercentage = (gradedQuestions > 0) ? (double) correctAnswers / gradedQuestions * 100 : 0;
            int possiblePoints = 100;
            int actualGradablePoints = ungradedEssays > 0 ?
                    (int) (possiblePoints * ((double) gradedQuestions / totalQuestions)) : possiblePoints;
            int earnedPoints = (int) (actualGradablePoints * scorePercentage / 100);

            System.out.printf("You received a %d on the test. The test was worth %d points",
                    earnedPoints, possiblePoints);

            if (ungradedEssays > 0) {
                System.out.printf(", but only %d of those points could be auto graded because there %s %d essay question%s.",
                        actualGradablePoints,
                        ungradedEssays == 1 ? "was" : "were",
                        ungradedEssays,
                        ungradedEssays == 1 ? "" : "s");
            } else {
                System.out.println(".");
            }

            // Save the result
            serializationHandler.saveTestResult(result);
        } else {
            System.out.println("Failed to load test response.");
        }
    }

    private List<String> getTestResponseFiles(String testName) {
        List<String> responseFiles = new ArrayList<>();
        File responseDir = new File("test_responses");

        if (!responseDir.exists()) {
            return responseFiles;
        }

        String testFilePrefix = testName.replaceAll("[^a-zA-Z0-9]", "_");
        File[] files = responseDir.listFiles((dir, name) ->
                name.startsWith(testFilePrefix) && name.contains("_response.ser"));

        if (files != null) {
            for (File file : files) {
                responseFiles.add(file.getName());
            }
        }

        return responseFiles;
    }

    private String formatResponseFileName(String fileName) {
        // Extract meaningful parts from filename like "TestName_RespondentName_timestamp_response.ser"
        String[] parts = fileName.split("_");
        if (parts.length >= 2) {
            String testName = parts[0];
            String respondentName = parts[1];
            return testName + " - " + respondentName;
        }
        return fileName;
    }

    private TestResponse loadTestResponse(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("test_responses" + File.separator + fileName))) {
            return (TestResponse) ois.readObject();
        } catch (Exception e) {
            System.err.println("Error loading test response: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        SurveyTestController controller = new SurveyTestController();
        controller.run();
    }
}