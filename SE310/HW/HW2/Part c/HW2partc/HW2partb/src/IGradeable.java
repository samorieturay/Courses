public interface IGradeable {
    boolean grade(Response response);
    String getCorrectAnswer();
    void setCorrectAnswer(String answer);
}