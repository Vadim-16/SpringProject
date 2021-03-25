package ca.purpose.edu.ExamFormers;

import ca.purpose.edu.ExamFormers.Extractors.Extractor;

import java.util.ArrayList;
import java.util.List;

public class ExamFormerImpl implements ExamFormer {
    private final List<String> questionsWithAnswers;
    private final List<Question> questionnaire = new ArrayList<>();

    public ExamFormerImpl(Extractor extractor) {
        questionsWithAnswers = extractor.extractQuestionsWithAnswers();
        prepareQuestions();
    }

    private void prepareQuestions() {
        for (String line : questionsWithAnswers) {
            Question question = new Question();
            question.setQuestion(line.split(",")[0]);
            question.setAnswer(line.split(",")[1]);
            this.questionnaire.add(question);
        }
    }

    public List<Question> getQuestionnaire() {
        return questionnaire;
    }

    public Question getQuestion(int questionNumber) {
        return this.questionnaire.get(questionNumber);
    }

}