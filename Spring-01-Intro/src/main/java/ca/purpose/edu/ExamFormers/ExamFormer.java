package ca.purpose.edu.ExamFormers;

import java.util.List;

public interface ExamFormer {
    List<Question> getQuestionnaire();
    Question pullRandomQuestion();
    Question getQuestion(int questionNumber);
}
