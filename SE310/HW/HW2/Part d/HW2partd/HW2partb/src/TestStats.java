import java.io.Serializable;

public class TestStats implements Serializable {
    private Test test;
    private int totalAttempts;
    private double averageScore;
    private double highestScore;
    private double lowestScore;
    private double passRate;
    private static final long serialVersionUID = 1L;

    public TestStats() {}

    public TestStats(Test test) {
        this.test = test;
    }

    public String generateSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("=== Test Statistics ===\n");
        summary.append("Test: ").append(test.getTestName()).append("\n");
        summary.append("Total Attempts: ").append(totalAttempts).append("\n");
        summary.append("Average Score: ").append(String.format("%.2f", averageScore)).append("%\n");
        summary.append("Highest Score: ").append(String.format("%.2f", highestScore)).append("%\n");
        summary.append("Lowest Score: ").append(String.format("%.2f", lowestScore)).append("%\n");
        summary.append("Pass Rate: ").append(String.format("%.2f", passRate)).append("%\n");

        return summary.toString();
    }

    // Getters and Setters
    public Test getTest() { return test; }
    public void setTest(Test test) { this.test = test; }
    public int getTotalAttempts() { return totalAttempts; }
    public void setTotalAttempts(int totalAttempts) { this.totalAttempts = totalAttempts; }
    public double getAverageScore() { return averageScore; }
    public void setAverageScore(double averageScore) { this.averageScore = averageScore; }
    public double getHighestScore() { return highestScore; }
    public void setHighestScore(double highestScore) { this.highestScore = highestScore; }
    public double getLowestScore() { return lowestScore; }
    public void setLowestScore(double lowestScore) { this.lowestScore = lowestScore; }
    public double getPassRate() { return passRate; }
    public void setPassRate(double passRate) { this.passRate = passRate; }
}