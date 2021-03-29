package ca.purpose.edu.examformers.extractors;

import ca.purpose.edu.examformers.Question;

import java.util.List;

public interface QuestionExtractor {
    List<Question> getQuestionnaire();
}
