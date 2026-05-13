public class Output {
    private String outputData;

    public Output() {}

    public void outputDriver() {
        // Main output processing method
    }

    public void printSurvey(Survey survey) {
        if (survey != null) {
            survey.printSurvey();
        } else {
            System.out.println("No survey to display.");
        }
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void printError(String errorMessage) {
        System.err.println("Error: " + errorMessage);
    }

    public String getOutputData() { return outputData; }
    public void setOutputData(String outputData) { this.outputData = outputData; }
}

