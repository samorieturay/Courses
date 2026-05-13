import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Test implements Serializable {
    private List<TestQuestion> testQuestions;
    private Survey survey; // Composition - Test contains a Survey
    private static final long serialVersionUID = 1L;

    public Test() {
        testQuestions = new ArrayList<>();
        survey = new Survey();
    }

    public Test(String testName, int testID) {
        testQuestions = new ArrayList<>();
        survey = new Survey(testName, testID);
    }

    public void printTest() {
        if (testQuestions.isEmpty()) {
            System.out.println("No questions in this test.");
            return;
        }

        System.out.println("\n=== " + survey.getSurveyName() + " (Test) ===");
        for (int i = 0; i < testQuestions.size(); i++) {
            TestQuestion testQuestion = testQuestions.get(i);
            testQuestion.displayQuestion();
            System.out.println();
        }
    }

    public void printTestWithAnswers() {
        if (testQuestions.isEmpty()) {
            System.out.println("No questions in this test.");
            return;
        }

        System.out.println("\n=== " + survey.getSurveyName() + " (Test with Answers) ===");
        for (int i = 0; i < testQuestions.size(); i++) {
            TestQuestion testQuestion = testQuestions.get(i);
            testQuestion.displayQuestionWithAnswer();
            System.out.println();
        }
    }

    public void addTestQuestion(TestQuestion testQuestion) {
        testQuestions.add(testQuestion);
        // add the base question to the survey for consistency
        survey.addQuestion(testQuestion.getBaseQuestion());
    }

    public TestQuestion getTestQuestion(int index) {
        if (index >= 0 && index < testQuestions.size()) {
            return testQuestions.get(index);
        }
        return null;
    }

    // Getters and Setters
    public List<TestQuestion> getTestQuestions() { return testQuestions; }
    public void setTestQuestions(List<TestQuestion> testQuestions) { this.testQuestions = testQuestions; }
    public Survey getSurvey() { return survey; }
    public void setSurvey(Survey survey) { this.survey = survey; }
    public String getTestName() { return survey.getSurveyName(); }
    public void setTestName(String testName) { survey.setSurveyName(testName); }
    public int getTestID() { return survey.getSurveyID(); }
    public void setTestID(int testID) { survey.setSurveyID(testID); }
}