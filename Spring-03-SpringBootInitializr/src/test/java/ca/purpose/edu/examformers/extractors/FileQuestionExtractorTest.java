package ca.purpose.edu.examformers.extractors;

import ca.purpose.edu.examformers.MyTestConfig;
import ca.purpose.edu.models.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Import(MyTestConfig.class)
class FileQuestionExtractorTest {

    @Autowired
    private QuestionExtractor questionExtractor;

    @Test
    @DisplayName("test proper reading from file")
    void testGetQuestionnaire() {
        List<Question> questionnaire = questionExtractor.getQuestionnaire();
        Question question2 = questionnaire.get(1);
        assertEquals(question2.getQuestion(), "Как долго тестим?");
        assertEquals(question2.getAnswer(), "Это всё");
    }
}