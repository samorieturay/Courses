import java.util.List;

public class ResultsTabulator {

    public SurveyStats tabulateSurveyResults(List<SurveyResponse> responses) {
        if (responses.isEmpty()) {
            return new SurveyStats();
        }

        SurveyStats stats = new SurveyStats(responses.get(0).getSurvey());
        stats.setTotalResponses(responses.size());

        // Additional survey analysis could be added here

        return stats;
    }

    public TestStats tabulateTestResults(List<TestResult> results) {
        if (results.isEmpty()) {
            return new TestStats();
        }

        TestStats stats = new TestStats(results.get(0).getTest());
        stats.setTotalAttempts(results.size());

        double totalScore = 0;
        double highest = 0;
        double lowest = 100;
        int passes = 0;
        double passingScore = 60.0; // Default passing score

        for (TestResult result : results) {
            double score = result.getScorePercentage();
            totalScore += score;

            if (score > highest) highest = score;
            if (score < lowest) lowest = score;
            if (score >= passingScore) passes++;
        }

        stats.setAverageScore(totalScore / results.size());
        stats.setHighestScore(highest);
        stats.setLowestScore(lowest);
        stats.setPassRate((double) passes / results.size() * 100.0);

        return stats;
    }
}
