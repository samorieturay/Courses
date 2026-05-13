import java.io.File;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.*;

public class SurveyTestTabulator {

    public void tabulateSurvey(Survey survey) {
        System.out.println("\n=== Survey Tabulation ===");
        System.out.println("Survey: " + survey.getSurveyName());

        // Load all responses for this survey
        List<SurveyResponse> responses = loadSurveyResponses(survey.getSurveyName());

        if (responses.isEmpty()) {
            System.out.println("No responses found for this survey.");
            return;
        }

        System.out.println("Total responses: " + responses.size());
        System.out.println();

        // Tabulate each question
        for (int i = 0; i < survey.getQuestions().size(); i++) {
            Question question = survey.getQuestions().get(i);
            tabulateQuestion(question, responses, i);
            System.out.println();
        }
    }

    public void tabulateTest(Test test) {
        System.out.println("\n=== Test Tabulation ===");
        System.out.println("Test: " + test.getTestName());

        // Load all test responses
        List<TestResponse> responses = loadTestResponses(test.getTestName());

        if (responses.isEmpty()) {
            System.out.println("No responses found for this test.");
            return;
        }

        System.out.println("Total responses: " + responses.size());
        System.out.println();

        // Tabulate each question
        for (int i = 0; i < test.getTestQuestions().size(); i++) {
            TestQuestion testQuestion = test.getTestQuestions().get(i);
            Question question = testQuestion.getBaseQuestion();
            tabulateTestQuestion(question, responses, i);
            System.out.println();
        }
    }

    private void tabulateQuestion(Question question, List<SurveyResponse> responses, int questionIndex) {
        System.out.println("Question " + (questionIndex + 1) + ": " + question.getPrompt());

        if (question instanceof TrueFalseQuestion) {
            tabulateTrueFalse(responses, questionIndex);
        } else if (question instanceof MultipleChoiceQuestion) {
            tabulateMultipleChoice((MultipleChoiceQuestion) question, responses, questionIndex);
        } else if (question instanceof ShortAnswerQuestion && !(question instanceof EssayQuestion)) {
            tabulateShortAnswer(responses, questionIndex);
        } else if (question instanceof EssayQuestion) {
            tabulateEssay(responses, questionIndex);
        } else if (question instanceof DateQuestion) {
            tabulateDate(responses, questionIndex);
        } else if (question instanceof MatchingQuestion) {
            tabulateMatching(responses, questionIndex);
        }
    }

    private void tabulateTestQuestion(Question question, List<TestResponse> responses, int questionIndex) {
        System.out.println("Question " + (questionIndex + 1) + ": " + question.getPrompt());

        if (question instanceof TrueFalseQuestion) {
            tabulateTestTrueFalse(responses, questionIndex);
        } else if (question instanceof MultipleChoiceQuestion) {
            tabulateTestMultipleChoice((MultipleChoiceQuestion) question, responses, questionIndex);
        } else if (question instanceof ShortAnswerQuestion && !(question instanceof EssayQuestion)) {
            tabulateTestShortAnswer(responses, questionIndex);
        } else if (question instanceof EssayQuestion) {
            tabulateTestEssay(responses, questionIndex);
        } else if (question instanceof DateQuestion) {
            tabulateTestDate(responses, questionIndex);
        } else if (question instanceof MatchingQuestion) {
            tabulateTestMatching(responses, questionIndex);
        }
    }

    // True/False tabulation for surveys
    private void tabulateTrueFalse(List<SurveyResponse> responses, int questionIndex) {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("T", 0);
        counts.put("F", 0);

        for (SurveyResponse response : responses) {
            if (questionIndex < response.getResponses().size()) {
                Response r = response.getResponses().get(questionIndex);
                if (r instanceof SingularResponse) {
                    String answer = ((SingularResponse) r).getResponseString().trim().toUpperCase();
                    if (answer.equals("T") || answer.equals("TRUE")) {
                        counts.put("T", counts.get("T") + 1);
                    } else if (answer.equals("F") || answer.equals("FALSE")) {
                        counts.put("F", counts.get("F") + 1);
                    }
                }
            }
        }

        System.out.println("True: " + counts.get("T"));
        System.out.println("False: " + counts.get("F"));
    }

    // True/False tabulation for tests
    private void tabulateTestTrueFalse(List<TestResponse> responses, int questionIndex) {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("T", 0);
        counts.put("F", 0);

        for (TestResponse response : responses) {
            if (questionIndex < response.getResponses().size()) {
                Response r = response.getResponses().get(questionIndex);
                if (r instanceof SingularResponse) {
                    String answer = ((SingularResponse) r).getResponseString().trim().toUpperCase();
                    if (answer.equals("T") || answer.equals("TRUE")) {
                        counts.put("T", counts.get("T") + 1);
                    } else if (answer.equals("F") || answer.equals("FALSE")) {
                        counts.put("F", counts.get("F") + 1);
                    }
                }
            }
        }

        System.out.println("True: " + counts.get("T"));
        System.out.println("False: " + counts.get("F"));
    }

    // Multiple Choice tabulation for surveys
    private void tabulateMultipleChoice(MultipleChoiceQuestion question, List<SurveyResponse> responses, int questionIndex) {
        Map<String, Integer> counts = new HashMap<>();

        // Initialize counts for all choices
        for (int i = 0; i < question.getChoices().size(); i++) {
            char letter = (char) ('A' + i);
            counts.put(String.valueOf(letter), 0);
        }

        for (SurveyResponse response : responses) {
            if (questionIndex < response.getResponses().size()) {
                Response r = response.getResponses().get(questionIndex);
                if (r instanceof SingularResponse) {
                    String answer = ((SingularResponse) r).getResponseString().trim().toUpperCase();
                    if (counts.containsKey(answer)) {
                        counts.put(answer, counts.get(answer) + 1);
                    }
                }
            }
        }

        // Display results in order
        for (int i = 0; i < question.getChoices().size(); i++) {
            char letter = (char) ('A' + i);
            System.out.println(letter + ": " + counts.get(String.valueOf(letter)));
        }
    }

    // Multiple Choice tabulation for tests
    private void tabulateTestMultipleChoice(MultipleChoiceQuestion question, List<TestResponse> responses, int questionIndex) {
        Map<String, Integer> counts = new HashMap<>();

        // Initialize counts for all choices
        for (int i = 0; i < question.getChoices().size(); i++) {
            char letter = (char) ('A' + i);
            counts.put(String.valueOf(letter), 0);
        }

        for (TestResponse response : responses) {
            if (questionIndex < response.getResponses().size()) {
                Response r = response.getResponses().get(questionIndex);
                if (r instanceof SingularResponse) {
                    String answer = ((SingularResponse) r).getResponseString().trim().toUpperCase();
                    if (counts.containsKey(answer)) {
                        counts.put(answer, counts.get(answer) + 1);
                    }
                }
            }
        }

        // Display results in order
        for (int i = 0; i < question.getChoices().size(); i++) {
            char letter = (char) ('A' + i);
            System.out.println(letter + ": " + counts.get(String.valueOf(letter)));
        }
    }

    // Short Answer tabulation for surveys
    private void tabulateShortAnswer(List<SurveyResponse> responses, int questionIndex) {
        Map<String, Integer> counts = new HashMap<>();

        for (SurveyResponse response : responses) {
            if (questionIndex < response.getResponses().size()) {
                Response r = response.getResponses().get(questionIndex);
                if (r instanceof SingularResponse) {
                    String answer = ((SingularResponse) r).getResponseString().trim();
                    counts.put(answer, counts.getOrDefault(answer, 0) + 1);
                }
            }
        }

        // Sort by count descending
        counts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }

    // Short Answer tabulation for tests
    private void tabulateTestShortAnswer(List<TestResponse> responses, int questionIndex) {
        Map<String, Integer> counts = new HashMap<>();

        for (TestResponse response : responses) {
            if (questionIndex < response.getResponses().size()) {
                Response r = response.getResponses().get(questionIndex);
                if (r instanceof SingularResponse) {
                    String answer = ((SingularResponse) r).getResponseString().trim();
                    counts.put(answer, counts.getOrDefault(answer, 0) + 1);
                }
            }
        }

        // Sort by count descending
        counts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }

    // Essay tabulation - just list all responses
    private void tabulateEssay(List<SurveyResponse> responses, int questionIndex) {
        for (SurveyResponse response : responses) {
            if (questionIndex < response.getResponses().size()) {
                Response r = response.getResponses().get(questionIndex);
                if (r instanceof SingularResponse) {
                    String answer = ((SingularResponse) r).getResponseString().trim();
                    System.out.println(answer);
                }
            }
        }
    }

    // Essay tabulation for tests
    private void tabulateTestEssay(List<TestResponse> responses, int questionIndex) {
        for (TestResponse response : responses) {
            if (questionIndex < response.getResponses().size()) {
                Response r = response.getResponses().get(questionIndex);
                if (r instanceof SingularResponse) {
                    String answer = ((SingularResponse) r).getResponseString().trim();
                    System.out.println(answer);
                }
            }
        }
    }

    // Date tabulation
    private void tabulateDate(List<SurveyResponse> responses, int questionIndex) {
        Map<String, Integer> counts = new HashMap<>();

        for (SurveyResponse response : responses) {
            if (questionIndex < response.getResponses().size()) {
                Response r = response.getResponses().get(questionIndex);
                if (r instanceof SingularResponse) {
                    String answer = ((SingularResponse) r).getResponseString().trim();
                    counts.put(answer, counts.getOrDefault(answer, 0) + 1);
                }
            }
        }

        // Sort by count descending
        counts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> {
                    System.out.println(entry.getKey());
                    System.out.println(entry.getValue());
                });
    }

    // Date tabulation for tests
    private void tabulateTestDate(List<TestResponse> responses, int questionIndex) {
        Map<String, Integer> counts = new HashMap<>();

        for (TestResponse response : responses) {
            if (questionIndex < response.getResponses().size()) {
                Response r = response.getResponses().get(questionIndex);
                if (r instanceof SingularResponse) {
                    String answer = ((SingularResponse) r).getResponseString().trim();
                    counts.put(answer, counts.getOrDefault(answer, 0) + 1);
                }
            }
        }

        // Sort by count descending
        counts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> {
                    System.out.println(entry.getKey());
                    System.out.println(entry.getValue());
                });
    }

    // Matching tabulation
    private void tabulateMatching(List<SurveyResponse> responses, int questionIndex) {
        Map<String, Integer> permutationCounts = new HashMap<>();

        for (SurveyResponse response : responses) {
            if (questionIndex < response.getResponses().size()) {
                Response r = response.getResponses().get(questionIndex);
                if (r instanceof SingularResponse) {
                    String answer = ((SingularResponse) r).getResponseString().trim();
                    permutationCounts.put(answer, permutationCounts.getOrDefault(answer, 0) + 1);
                }
            }
        }

        // Display each permutation and its count
        permutationCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> {
                    System.out.println(entry.getKey());
                    System.out.println(entry.getValue());
                });
    }

    // Matching tabulation for tests
    private void tabulateTestMatching(List<TestResponse> responses, int questionIndex) {
        Map<String, Integer> permutationCounts = new HashMap<>();

        for (TestResponse response : responses) {
            if (questionIndex < response.getResponses().size()) {
                Response r = response.getResponses().get(questionIndex);
                if (r instanceof SingularResponse) {
                    String answer = ((SingularResponse) r).getResponseString().trim();
                    permutationCounts.put(answer, permutationCounts.getOrDefault(answer, 0) + 1);
                }
            }
        }

        // Display each permutation and its count
        permutationCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> {
                    System.out.println(entry.getKey());
                    System.out.println(entry.getValue());
                });
    }

    // Load survey responses from files
    private List<SurveyResponse> loadSurveyResponses(String surveyName) {
        List<SurveyResponse> responses = new ArrayList<>();
        File responseDir = new File("responses");

        if (!responseDir.exists()) {
            return responses;
        }

        File[] files = responseDir.listFiles((dir, name) ->
                name.startsWith(surveyName.replaceAll("[^a-zA-Z0-9]", "_")) && name.endsWith(".ser"));

        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    SurveyResponse response = (SurveyResponse) ois.readObject();
                    responses.add(response);
                } catch (Exception e) {
                    System.err.println("Error loading response file: " + file.getName());
                }
            }
        }

        return responses;
    }

    // Load test responses from files
    private List<TestResponse> loadTestResponses(String testName) {
        List<TestResponse> responses = new ArrayList<>();
        File responseDir = new File("test_responses");

        if (!responseDir.exists()) {
            return responses;
        }

        File[] files = responseDir.listFiles((dir, name) ->
                name.startsWith(testName.replaceAll("[^a-zA-Z0-9]", "_")) && name.contains("_response.ser"));

        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    TestResponse response = (TestResponse) ois.readObject();
                    responses.add(response);
                } catch (Exception e) {
                    System.err.println("Error loading test response file: " + file.getName());
                }
            }
        }

        return responses;
    }
}
