import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class SerializationHandler {
    private static final String SURVEYS_DIR = "surveys";
    private static final String TESTS_DIR = "tests";
    private static final String RESPONSES_DIR = "responses";
    private static final String TEST_RESPONSES_DIR = "test_responses";
    private static final String TEST_RESULTS_DIR = "test_results";

    public SerializationHandler() {
        createDirectories();
    }

    private void createDirectories() {
        try {
            Files.createDirectories(Paths.get(SURVEYS_DIR));
            Files.createDirectories(Paths.get(TESTS_DIR));
            Files.createDirectories(Paths.get(RESPONSES_DIR));
            Files.createDirectories(Paths.get(TEST_RESPONSES_DIR));
            Files.createDirectories(Paths.get(TEST_RESULTS_DIR));
        } catch (IOException e) {
            System.err.println("Error creating directories: " + e.getMessage());
        }
    }

    public void serialize(Object obj, String fileName, String directory) {
        String fullPath = directory + File.separator + fileName;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fullPath))) {
            oos.writeObject(obj);
            System.out.println("Successfully saved to: " + fullPath);
        } catch (IOException e) {
            System.err.println("Error serializing object: " + e.getMessage());
        }
    }

    public Object deserialize(String fileName, String directory) {
        String fullPath = directory + File.separator + fileName;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fullPath))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error deserializing object: " + e.getMessage());
            return null;
        }
    }

    // Survey methods (existing)
    public void saveSurvey(Survey survey) {
        String fileName = survey.getSurveyName().replaceAll("[^a-zA-Z0-9]", "_") + ".ser";
        serialize(survey, fileName, SURVEYS_DIR);
        survey.setFile_name(fileName);
        survey.setFile_loc(SURVEYS_DIR);
    }

    public Survey loadSurvey(String fileName) {
        return (Survey) deserialize(fileName, SURVEYS_DIR);
    }

    public void saveSurveyResponse(SurveyResponse response) {
        String fileName = response.getSurvey().getSurveyName() + "_" +
                response.getRespondentName() + "_" +
                System.currentTimeMillis() + ".ser";
        fileName = fileName.replaceAll("[^a-zA-Z0-9_]", "_");
        serialize(response, fileName, RESPONSES_DIR);
    }

    // Test methods (new for Part C)
    public void saveTest(Test test) {
        String fileName = test.getTestName().replaceAll("[^a-zA-Z0-9]", "_") + "_test.ser";
        serialize(test, fileName, TESTS_DIR);
    }

    public Test loadTest(String fileName) {
        return (Test) deserialize(fileName, TESTS_DIR);
    }

    public void saveTestResponse(TestResponse response) {
        String fileName = response.getTest().getTestName() + "_" +
                response.getRespondentName() + "_" +
                System.currentTimeMillis() + "_response.ser";
        fileName = fileName.replaceAll("[^a-zA-Z0-9_]", "_");
        serialize(response, fileName, TEST_RESPONSES_DIR);
    }

    public void saveTestResult(TestResult result) {
        String fileName = result.getTest().getTestName() + "_" +
                result.getTestResponse().getRespondentName() + "_" +
                System.currentTimeMillis() + "_result.ser";
        fileName = fileName.replaceAll("[^a-zA-Z0-9_]", "_");
        serialize(result, fileName, TEST_RESULTS_DIR);
    }

    public List<String> getAvailableSurveys() {
        List<String> surveyFiles = new ArrayList<>();
        File surveysDirectory = new File(SURVEYS_DIR);

        if (surveysDirectory.exists() && surveysDirectory.isDirectory()) {
            File[] files = surveysDirectory.listFiles((dir, name) -> name.endsWith(".ser"));
            if (files != null) {
                for (File file : files) {
                    surveyFiles.add(file.getName());
                }
            }
        }
        return surveyFiles;
    }

    public List<String> getAvailableTests() {
        List<String> testFiles = new ArrayList<>();
        File testsDirectory = new File(TESTS_DIR);

        if (testsDirectory.exists() && testsDirectory.isDirectory()) {
            File[] files = testsDirectory.listFiles((dir, name) -> name.endsWith("_test.ser"));
            if (files != null) {
                for (File file : files) {
                    testFiles.add(file.getName());
                }
            }
        }
        return testFiles;
    }

    public List<TestResult> loadAllTestResults() {
        List<TestResult> results = new ArrayList<>();
        File resultsDirectory = new File(TEST_RESULTS_DIR);

        if (resultsDirectory.exists() && resultsDirectory.isDirectory()) {
            File[] files = resultsDirectory.listFiles((dir, name) -> name.endsWith("_result.ser"));
            if (files != null) {
                for (File file : files) {
                    TestResult result = (TestResult) deserialize(file.getName(), TEST_RESULTS_DIR);
                    if (result != null) {
                        results.add(result);
                    }
                }
            }
        }
        return results;
    }
}
