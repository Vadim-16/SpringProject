package ca.purpose.edu.examformers;


import ca.purpose.edu.examformers.extractors.FileQuestionExtractor;
import ca.purpose.edu.examformers.extractors.QuestionExtractor;
import ca.purpose.edu.models.Question;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ExamFormerImplTest {

    @MockBean
    @Qualifier("FileQuestionExtractor")
    private QuestionExtractor questionExtractor;

    @Autowired
    private ExamFormer examFormer;

    private static List<Question> testQuestions;

    @Test
    @DisplayName("test random pull")
    void testPullRandomQuestion() {
        Question question1 = examFormer.pullRandomQuestion();
        assertTrue(testQuestions.contains(question1));
    }


    @Test
    @DisplayName("test proper questionnaire return")
    void testGetQuestionnaire() {
        List<Question> questionnaire = examFormer.getQuestionnaire();
        assertEquals(questionnaire, testQuestions);
    }

    @Test
    @DisplayName("test proper question return")
    void testGetQuestion() {
        Question question1 = examFormer.getQuestion(0);
        assertEquals(question1, testQuestions.get(0));
    }

    @BeforeAll
    static void createQuestions() {
        Question question1 = new Question();
        question1.setQuestion("Это тест?");
        question1.setAnswer("Да");
        Question question2 = new Question();
        question2.setQuestion("Как долго тестим?");
        question2.setAnswer("Это всё");
        ArrayList<Question> testQuestions = new ArrayList<>();
        testQuestions.add(question1);
        testQuestions.add(question2);
        ExamFormerImplTest.testQuestions = testQuestions;
    }

    @AfterAll
    static void clearQuestions() {
        testQuestions = null;
    }
}