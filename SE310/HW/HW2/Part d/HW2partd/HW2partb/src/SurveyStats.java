import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SurveyStats implements Serializable {
    private Survey survey;
    private int totalResponses;
    private Map<String, Object> responseData;
    private static final long serialVersionUID = 1L;

    public SurveyStats() {
        responseData = new HashMap<>();
    }

    public SurveyStats(Survey survey) {
        this.survey = survey;
        this.responseData = new HashMap<>();
    }

    public String generateSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("=== Survey Statistics ===\n");
        summary.append("Survey: ").append(survey.getSurveyName()).append("\n");
        summary.append("Total Responses: ").append(totalResponses).append("\n");
        summary.append("Total Questions: ").append(survey.getQuestions().size()).append("\n");

        return summary.toString();
    }

    // Getters and Setters
    public Survey getSurvey() { return survey; }
    public void setSurvey(Survey survey) { this.survey = survey; }
    public int getTotalResponses() { return totalResponses; }
    public void setTotalResponses(int totalResponses) { this.totalResponses = totalResponses; }
    public Map<String, Object> getResponseData() { return responseData; }
    public void setResponseData(Map<String, Object> responseData) { this.responseData = responseData; }
}
