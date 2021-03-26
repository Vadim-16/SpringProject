package ca.purpose.edu.ExamFormers.Extractors;

import ca.purpose.edu.ExamFormers.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileQuestionExtractor implements QuestionExtractor{
    private final List<Question> questionnaire;

    public FileQuestionExtractor(String examPath) {
        this.questionnaire = extractQuestionnaire(examPath);
    }

    private List<Question> extractQuestionnaire(String examPath) {
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

    @Override
    public List<Question> getQuestionnaire() {
        return questionnaire;
    }
}
