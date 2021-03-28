package ca.purpose.edu.examFormers;

import ca.purpose.edu.examFormers.extractors.QuestionExtractor;


import java.util.List;

public class ExamFormerImpl implements ExamFormer {
    private final List<Question> questionnaire;

    public ExamFormerImpl(QuestionExtractor questionExtractor) {
        this.questionnaire = questionExtractor.getQuestionnaire();
    }

    @Override
    public Question pullRandomQuestion() {
        double random = Math.random();
        if (random == 1) random -= 0.01;
        int randomNumber = (int) (random * questionnaire.size());
        return questionnaire.get(randomNumber);
    }

    @Override
    public List<Question> getQuestionnaire() {
        return questionnaire;
    }

    @Override
    public Question getQuestion(int questionNumber) {
        return this.questionnaire.get(questionNumber);
    }

}