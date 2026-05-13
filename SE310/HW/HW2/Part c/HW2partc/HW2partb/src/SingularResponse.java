public class SingularResponse extends Response {
    private String responseString;

    public SingularResponse() { super(); }

    public SingularResponse(int questionID, int responseID, String responseString) {
        super(questionID, responseID);
        this.responseString = responseString;
    }

    @Override
    public boolean isValid() {
        return responseString != null && !responseString.trim().isEmpty();
    }

    public String getResponseString() { return responseString; }
    public void setResponseString(String responseString) { this.responseString = responseString; }
}