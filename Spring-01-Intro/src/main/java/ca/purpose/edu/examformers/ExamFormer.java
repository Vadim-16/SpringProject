package ca.purpose.edu.examformers;

import java.util.List;

public interface ExamFormer {
    List<Question> getQuestionnaire();
    Question pullRandomQuestion();
    Question getQuestion(int questionNumber);
}
