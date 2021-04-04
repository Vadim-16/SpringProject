package ca.purpose.edu.examformers;

import ca.purpose.edu.examformers.extractors.FileQuestionExtractor;
import ca.purpose.edu.examformers.extractors.QuestionExtractor;
import ca.purpose.edu.models.Question;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Stubber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ExamFormerImplTest {

    @Autowired
    @Qualifier("fileQuestionExtractor")
    private QuestionExtractor fileQuestionExtractor;

    @Autowired
    private ExamFormer examFormer;

    private static List<Question> testQuestions;



    @Test
    @DisplayName("test random pull")
    void testPullRandomQuestion() {
//        ExamFormerImpl examFormer = new ExamFormerImpl(fileQuestionExtractor);
        Question question1 = examFormer.pullRandomQuestion();
        assertEquals(question1, testQuestions.get(0));
    }

//    @Test
//    @DisplayName("test proper questionnaire return")
//    void testGetQuestionnaire() {
//        Mockito.when(fileQuestionExtractor.getQuestionnaire()).thenReturn(testQuestions);
//        ExamFormerImpl examFormer = new ExamFormerImpl(fileQuestionExtractor);
//        List<Question> questionnaire = examFormer.getQuestionnaire();
//        assertEquals(questionnaire, testQuestions);
//    }
//
//    @Test
//    @DisplayName("test proper question return")
//    void testGetQuestion() {
//        Mockito.when(fileQuestionExtractor.getQuestionnaire()).thenReturn(testQuestions);
//        ExamFormerImpl examFormer = new ExamFormerImpl(fileQuestionExtractor);
//        Question question1 = examFormer.getQuestion(0);
//        assertEquals(question1, testQuestions.get(0));
//    }

    @BeforeAll
    static void createQuestions() {
        Question question = new Question();
        question.setQuestion("testing?");
        question.setAnswer("yes");
        ArrayList<Question> testQuestions = new ArrayList<>();
        testQuestions.add(question);
        ExamFormerImplTest.testQuestions = testQuestions;
    }

    @AfterAll
    static void clearQuestions() {
        testQuestions = null;
    }
}