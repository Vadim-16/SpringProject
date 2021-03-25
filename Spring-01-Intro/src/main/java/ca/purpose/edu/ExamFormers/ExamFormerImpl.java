package ca.purpose.edu.ExamFormers;

import ca.purpose.edu.ExamFormers.Extractors.Extractor;

import java.util.ArrayList;
import java.util.List;

public class ExamFormerImpl implements ExamFormer {
    private final List<Question> questionnaire;

    public ExamFormerImpl(Extractor extractor) {
        List<String> questionsWithAnswers = extractor.extractQuestionsWithAnswers();
        this.questionnaire = prepareQuestions(questionsWithAnswers);
    }

    private List<Question> prepareQuestions(List<String> questionsWithAnswers) {
        List<Question> questionnaire = new ArrayList<>();
        for (String line : questionsWithAnswers) {
            Question question = new Question();
            question.setQuestion(line.split(",")[0]);
            question.setAnswer(line.split(",")[1]);
            questionnaire.add(question);
        }
        return questionnaire;
    }

    public List<Question> getQuestionnaire() {
        return questionnaire;
    }

    public Question getQuestion(int questionNumber) {
        return this.questionnaire.get(questionNumber);
    }

}