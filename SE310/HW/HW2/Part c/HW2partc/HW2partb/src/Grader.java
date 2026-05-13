import java.util.List;

public class Grader {

    public TestResult gradeTest(Test test, TestResponse testResponse) {
        TestResult result = new TestResult(test, testResponse);
        return result;
    }

    public boolean gradeQuestion(TestQuestion testQuestion, Response response) {
        return testQuestion.grade(response);
    }

    public void displayDetailedResults(TestResult result) {
        System.out.println(result.generateReport());

        Test test = result.getTest();
        TestResponse testResponse = result.getTestResponse();
        List<TestQuestion> questions = test.getTestQuestions();
        List<Response> responses = testResponse.getResponses();

        System.out.println("\n=== Question by Question Results ===");

        for (int i = 0; i < Math.min(questions.size(), responses.size()); i++) {
            TestQuestion testQuestion = questions.get(i);
            Response response = responses.get(i);

            System.out.println("\nQuestion " + (i + 1) + ":");
            System.out.println(testQuestion.getBaseQuestion().getPrompt());

            if (testQuestion.getBaseQuestion() instanceof EssayQuestion) {
                System.out.println("Answer: [Essay - Manual Grading Required]");
                System.out.println("Status: Not Graded");
            } else {
                String studentAnswer = "";
                if (response instanceof SingularResponse) {
                    studentAnswer = ((SingularResponse) response).getResponseString();
                }

                System.out.println("Student Answer: " + studentAnswer);
                System.out.println("Correct Answer: " + testQuestion.getCorrectAnswer());

                boolean correct = testQuestion.grade(response);
                System.out.println("Status: " + (correct ? "CORRECT" : "INCORRECT"));
            }
        }
    }
}
