package ca.purpose.edu.examformers.extractors;

import ca.purpose.edu.models.Question;

import java.util.List;

public interface QuestionExtractor {
    List<Question> getQuestionnaire();
}
