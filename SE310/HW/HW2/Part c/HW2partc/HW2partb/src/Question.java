import java.io.Serializable;

public abstract class Question implements IQuestion, Serializable {
    protected String prompt;
    protected String type;
    protected int id;
    protected Response questionResponse;
    protected boolean allowMultipleResponses;
    private static final long serialVersionUID = 1L;

    public Question() {}

    public Question(String prompt, String type, int id) {
        this.prompt = prompt;
        this.type = type;
        this.id = id;
        this.allowMultipleResponses = false;
    }

    @Override
    public String getPrompt() { return prompt; }

    @Override
    public void setPrompt(String prompt) { this.prompt = prompt; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Response getQuestionResponse() { return questionResponse; }
    public void setQuestionResponse(Response questionResponse) { this.questionResponse = questionResponse; }
    public boolean getAllowMultipleResponses() { return allowMultipleResponses; }
    public void setAllowMultipleResponses(boolean allowMultipleResponses) { this.allowMultipleResponses = allowMultipleResponses; }

    public abstract void editResponse();
    public abstract void editQuestion();
    public abstract String takeQuestion();
}
