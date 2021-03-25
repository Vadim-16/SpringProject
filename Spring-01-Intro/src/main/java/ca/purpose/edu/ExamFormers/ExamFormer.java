package ca.purpose.edu.ExamFormers;

import java.util.List;

public interface ExamFormer {
    List<Question> getQuestionnaire();
    Question getQuestion(int questionNumber);
}
