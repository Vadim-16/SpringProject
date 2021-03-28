package ca.purpose.edu.examFormers;

import ca.purpose.edu.examFormers.extractors.QuestionExtractor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamFormerImpl implements ExamFormer {
    private final List<Question> questionnaire;

    public ExamFormerImpl(@Qualifier("fileQuestionExtractor") QuestionExtractor questionExtractor) {
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