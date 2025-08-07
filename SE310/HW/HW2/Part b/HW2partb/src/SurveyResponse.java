import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SurveyResponse implements Serializable {
    private Survey survey;
    private List<Response> responses;
    private String respondentName;
    private static final long serialVersionUID = 1L;

    public SurveyResponse() {
        responses = new ArrayList<>();
    }

    public SurveyResponse(Survey survey, String respondentName) {
        this.survey = survey;
        this.respondentName = respondentName;
        this.responses = new ArrayList<>();
    }

    public void addResponse(Response response) {
        responses.add(response);
    }

    // My Getters and Setters
    public Survey getSurvey() { return survey; }
    public void setSurvey(Survey survey) { this.survey = survey; }
    public List<Response> getResponses() { return responses; }
    public void setResponses(List<Response> responses) { this.responses = responses; }
    public String getRespondentName() { return respondentName; }
    public void setRespondentName(String respondentName) { this.respondentName = respondentName; }
}

