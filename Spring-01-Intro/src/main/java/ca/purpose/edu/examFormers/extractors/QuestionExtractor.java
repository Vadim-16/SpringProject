package ca.purpose.edu.examFormers.extractors;

import ca.purpose.edu.examFormers.Question;

import java.util.List;

public interface QuestionExtractor {
    List<Question> getQuestionnaire();
}
