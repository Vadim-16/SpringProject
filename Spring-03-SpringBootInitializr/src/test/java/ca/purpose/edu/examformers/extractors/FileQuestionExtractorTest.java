package ca.purpose.edu.examformers.extractors;

import ca.purpose.edu.models.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileQuestionExtractorTest {

    @Test
    @DisplayName("test proper reading from file")
    void testGetQuestionnaire() {
        FileQuestionExtractor fileQuestionExtractor = new FileQuestionExtractor(".\\src\\test\\test-resources\\test-questions_ru.csv");
        List<Question> questionnaire = fileQuestionExtractor.getQuestionnaire();

        Question question2 = questionnaire.get(1);
        assertEquals(question2.getQuestion(), "Как долго тестим?");
        assertEquals(question2.getAnswer(), "Это всё");
    }
}