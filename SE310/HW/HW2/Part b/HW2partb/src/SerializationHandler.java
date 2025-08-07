import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class SerializationHandler {
    private static final String SURVEYS_DIR = "surveys";
    private static final String RESPONSES_DIR = "responses";

    public SerializationHandler() {
        createDirectories();
    }

    private void createDirectories() {
        try {
            Files.createDirectories(Paths.get(SURVEYS_DIR));
            Files.createDirectories(Paths.get(RESPONSES_DIR));
        } catch (IOException e) {
            System.err.println("Error creating directories: " + e.getMessage());
        }
    }

    public void serialize(Object obj, String fileName, String directory) {
        String fullPath = directory + File.separator + fileName;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fullPath))) {
            oos.writeObject(obj);
            System.out.println("Successfully saved to: " + fullPath);
        } catch (IOException e) {
            System.err.println("Error serializing object: " + e.getMessage());
        }
    }

    public Object deserialize(String fileName, String directory) {
        String fullPath = directory + File.separator + fileName;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fullPath))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error deserializing object: " + e.getMessage());
            return null;
        }
    }

    public void saveSurvey(Survey survey) {
        String fileName = survey.getSurveyName().replaceAll("[^a-zA-Z0-9]", "_") + ".ser";
        serialize(survey, fileName, SURVEYS_DIR);
        survey.setFile_name(fileName);
        survey.setFile_loc(SURVEYS_DIR);
    }

    public Survey loadSurvey(String fileName) {
        return (Survey) deserialize(fileName, SURVEYS_DIR);
    }

    public void saveSurveyResponse(SurveyResponse response) {
        String fileName = response.getSurvey().getSurveyName() + "_" +
                response.getRespondentName() + "_" +
                System.currentTimeMillis() + ".ser";
        fileName = fileName.replaceAll("[^a-zA-Z0-9_]", "_");
        serialize(response, fileName, RESPONSES_DIR);
    }

    public List<String> getAvailableSurveys() {
        List<String> surveyFiles = new ArrayList<>();
        File surveysDirectory = new File(SURVEYS_DIR);

        if (surveysDirectory.exists() && surveysDirectory.isDirectory()) {
            File[] files = surveysDirectory.listFiles((dir, name) -> name.endsWith(".ser"));
            if (files != null) {
                for (File file : files) {
                    surveyFiles.add(file.getName());
                }
            }
        }
        return surveyFiles;
    }
}
