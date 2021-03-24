package ca.purpose.edu.ExamFormers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExamFormerImpl implements ExamFormer {
    private List<String> questionsWithAnswers;

    public ExamFormerImpl(String examPath) {
        this.questionsWithAnswers = extractQuestionsWithAnswers(examPath);
    }

    private List<String> extractQuestionsWithAnswers(String examPath) {
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

    @Override
    public List<String> parseQuestions() {
        List<String> questions = new ArrayList<>();
        for (String line : questionsWithAnswers) {
            questions.add(line.split(",")[0]);
        }
        return questions;
    }

    @Override
    public List<String> parseAnswers() {
        List<String> questions = new ArrayList<>();
        for (String line : questionsWithAnswers) {
            questions.add(line.split(",")[1]);
        }
        return questions;
    }

}
