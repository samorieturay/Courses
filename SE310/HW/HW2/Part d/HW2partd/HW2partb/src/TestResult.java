import java.io.Serializable;
import java.util.List;

public class TestResult implements Serializable {
    private Test test;
    private TestResponse testResponse;
    private int totalQuestions;
    private int correctAnswers;
    private int gradedQuestions;
    private int ungradedEssays;
    private double score;
    private static final long serialVersionUID = 1L;

    public TestResult() {}

    public TestResult(Test test, TestResponse testResponse) {
        this.test = test;
        this.testResponse = testResponse;
        this.totalQuestions = test.getTestQuestions().size();
        calculateScore();
    }

    public void calculateScore() {
        correctAnswers = 0;
        gradedQuestions = 0;
        ungradedEssays = 0;

        List<TestQuestion> testQuestions = test.getTestQuestions();
        List<Response> responses = testResponse.getResponses();

        for (int i = 0; i < Math.min(testQuestions.size(), responses.size()); i++) {
            TestQuestion testQuestion = testQuestions.get(i);
            Response response = responses.get(i);

            // Skip essay questions (they are not auto-graded)
            if (testQuestion.getBaseQuestion() instanceof EssayQuestion) {
                ungradedEssays++;
                continue;
            }

            gradedQuestions++;
            if (testQuestion.grade(response)) {
                correctAnswers++;
            }
        }

        // Calculate score based on graded questions only
        if (gradedQuestions > 0) {
            score = (double) correctAnswers / gradedQuestions * 100.0;
        } else {
            score = 0.0;
        }
    }

    public double getScorePercentage() {
        return score;
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== Test Results ===\n");
        report.append("Test: ").append(test.getTestName()).append("\n");
        report.append("Student: ").append(testResponse.getRespondentName()).append("\n");
        report.append("Total Questions: ").append(totalQuestions).append("\n");
        report.append("Graded Questions: ").append(gradedQuestions).append("\n");
        report.append("Correct Answers: ").append(correctAnswers).append("\n");
        report.append("Score: ").append(String.format("%.2f", score)).append("%\n");

        if (ungradedEssays > 0) {
            report.append("Note: ").append(ungradedEssays)
                    .append(" essay question(s) require manual grading.\n");
        }

        return report.toString();
    }

    // Getters and Setters
    public Test getTest() { return test; }
    public void setTest(Test test) { this.test = test; }
    public TestResponse getTestResponse() { return testResponse; }
    public void setTestResponse(TestResponse testResponse) { this.testResponse = testResponse; }
    public int getTotalQuestions() { return totalQuestions; }
    public int getCorrectAnswers() { return correctAnswers; }
    public int getGradedQuestions() { return gradedQuestions; }
    public int getUngradedEssays() { return ungradedEssays; }
    public double getScore() { return score; }
}