package ca.purpose.edu.examFormers.extractors;

import ca.purpose.edu.examFormers.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileQuestionExtractor implements QuestionExtractor {
    private final String examPath;

    public FileQuestionExtractor(String examPath) {
        this.examPath = examPath;
    }

    @Override
    public List<Question> getQuestionnaire() {
        List<Question> questionsWithAnswers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(examPath))) {
            while (reader.ready()) {
                String line = reader.readLine();
                Question question = new Question();
                question.setQuestion(line.split(",")[0]);
                question.setAnswer(line.split(",")[1]);
                questionsWithAnswers.add(question);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionsWithAnswers;
    }
}
