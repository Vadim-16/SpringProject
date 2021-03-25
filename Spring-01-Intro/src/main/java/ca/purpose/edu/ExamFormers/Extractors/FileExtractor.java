package ca.purpose.edu.ExamFormers.Extractors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileExtractor implements Extractor{
    private final String examPath;

    public FileExtractor(String examPath) {
        this.examPath = examPath;
    }

    public List<String> extractQuestionsWithAnswers() {
        List<String> questionsWithAnswers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(examPath))) {
            while (reader.ready()) {
                String line = reader.readLine();
                questionsWithAnswers.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionsWithAnswers;
    }
}
