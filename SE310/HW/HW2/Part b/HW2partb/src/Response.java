import java.io.Serializable;

public abstract class Response implements IResponse, Serializable {
    protected int questionID;
    protected int responseID;
    private static final long serialVersionUID = 1L;

    public Response() {}

    public Response(int questionID, int responseID) {
        this.questionID = questionID;
        this.responseID = responseID;
    }

    public int getQuestionID() { return questionID; }
    public void setQuestionID(int questionID) { this.questionID = questionID; }
    public int getResponseID() { return responseID; }
    public void setResponseID(int responseID) { this.responseID = responseID; }
}

