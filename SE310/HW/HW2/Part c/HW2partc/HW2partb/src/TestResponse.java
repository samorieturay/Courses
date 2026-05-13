import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestResponse implements Serializable {
    private Test test;
    private List<Response> responses;
    private String respondentName;
    private static final long serialVersionUID = 1L;

    public TestResponse() {
        responses = new ArrayList<>();
    }

    public TestResponse(Test test, String respondentName) {
        this.test = test;
        this.respondentName = respondentName;
        this.responses = new ArrayList<>();
    }

    public void addResponse(Response response) {
        responses.add(response);
    }

    // Getters and Setters
    public Test getTest() { return test; }
    public void setTest(Test test) { this.test = test; }
    public List<Response> getResponses() { return responses; }
    public void setResponses(List<Response> responses) { this.responses = responses; }
    public String getRespondentName() { return respondentName; }
    public void setRespondentName(String respondentName) { this.respondentName = respondentName; }
}