import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Survey implements Serializable {
    private List<Question> questions;
    private String surveyName;
    private int surveyID;
    private String file_loc;
    private String file_name;
    private static final long serialVersionUID = 1L;

    public Survey() {
        questions = new ArrayList<>();
    }

    public Survey(String surveyName, int surveyID) {
        this.surveyName = surveyName;
        this.surveyID = surveyID;
        this.questions = new ArrayList<>();
    }

    public void printSurvey() {
        if (questions.isEmpty()) {
            System.out.println("No questions in this survey.");
            return;
        }

        System.out.println("\n=== " + surveyName + " ===");
        for (Question question : questions) {
            question.displayQuestion();
            System.out.println();
        }
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void removeQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            questions.remove(index);
        }
    }

    public Question getQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            return questions.get(index);
        }
        return null;
    }

    // Getters and Setters
    public List<Question> getQuestions() { return questions; }
    public void setQuestions(List<Question> questions) { this.questions = questions; }
    public String getSurveyName() { return surveyName; }
    public void setSurveyName(String surveyName) { this.surveyName = surveyName; }
    public int getSurveyID() { return surveyID; }
    public void setSurveyID(int surveyID) { this.surveyID = surveyID; }
    public String getFile_loc() { return file_loc; }
    public void setFile_loc(String file_loc) { this.file_loc = file_loc; }
    public String getFile_name() { return file_name; }
    public void setFile_name(String file_name) { this.file_name = file_name; }
}
