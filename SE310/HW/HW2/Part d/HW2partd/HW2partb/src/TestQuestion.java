import java.io.Serializable;
import java.util.Map;

public class TestQuestion implements IGradeable, Serializable {
    private Question baseQuestion;
    private String correctAnswer;
    private static final long serialVersionUID = 1L;

    public TestQuestion() {}

    public TestQuestion(Question baseQuestion) {
        this.baseQuestion = baseQuestion;
    }

    public TestQuestion(Question baseQuestion, String correctAnswer) {
        this.baseQuestion = baseQuestion;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean grade(Response response) {
        if (response == null || correctAnswer == null) {
            return false;
        }

        // Don't grade essay questions
        if (baseQuestion instanceof EssayQuestion) {
            return false; // Essays are not auto-graded
        }

        String responseStr = "";
        if (response instanceof SingularResponse) {
            responseStr = ((SingularResponse) response).getResponseString();
        } else if (response instanceof MultiResponse) {
            // For matching questions, compare the entire response map
            if (baseQuestion instanceof MatchingQuestion) {
                return gradeMatchingResponse((MultiResponse) response);
            }
        }

        return correctAnswer.equalsIgnoreCase(responseStr.trim());
    }

    private boolean gradeMatchingResponse(MultiResponse response) {
        // Simple all-or-nothing grading for matching questions
        String[] correctPairs = correctAnswer.split(";");
        Map<String, String> responseMap = response.getResponseTable();

        for (String pair : correctPairs) {
            String[] parts = pair.split(":");
            if (parts.length == 2) {
                String key = parts[0].trim();
                String expectedValue = parts[1].trim();
                String actualValue = responseMap.get(key);

                if (!expectedValue.equals(actualValue)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public void setCorrectAnswer(String answer) {
        this.correctAnswer = answer;
    }

    public void displayQuestion() {
        if (baseQuestion != null) {
            baseQuestion.displayQuestion();
        }
    }

    public void displayQuestionWithAnswer() {
        if (baseQuestion != null) {
            baseQuestion.displayQuestion();
            System.out.println("Correct Answer: " + correctAnswer);
        }
    }

    public String takeQuestion() {
        if (baseQuestion != null) {
            return baseQuestion.takeQuestion();
        }
        return "";
    }

    public void editQuestion() {
        if (baseQuestion != null) {
            baseQuestion.editQuestion();
        }
    }

    public Question getBaseQuestion() {
        return baseQuestion;
    }

    public void setBaseQuestion(Question baseQuestion) {
        this.baseQuestion = baseQuestion;
    }
}