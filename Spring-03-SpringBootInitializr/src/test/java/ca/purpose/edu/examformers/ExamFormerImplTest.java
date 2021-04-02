package ca.purpose.edu.examformers;

import ca.purpose.edu.examformers.extractors.FileQuestionExtractor;
import ca.purpose.edu.models.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ExamFormerImplTest {
    @MockBean
    private FileQuestionExtractor fileQuestionExtractor;

    private List<Question> testQuestions;

    {
        Question question = new Question();
        question.setQuestion("testing?");
        question.setAnswer("yes");
        ArrayList<Question> testQuestions = new ArrayList<>();
        testQuestions.add(question);
        this.testQuestions = testQuestions;
    }

    @Test
    @DisplayName("test random pull")
    void testPullRandomQuestion() {
        Mockito.when(fileQuestionExtractor.getQuestionnaire()).thenReturn(testQuestions);
        ExamFormerImpl examFormer = new ExamFormerImpl(fileQuestionExtractor);
        Question question1 = examFormer.pullRandomQuestion();
        assertEquals(question1, testQuestions.get(0));
    }

    @Test
    @DisplayName("test proper questionnaire return")
    void testGetQuestionnaire() {
        Mockito.when(fileQuestionExtractor.getQuestionnaire()).thenReturn(testQuestions);
        ExamFormerImpl examFormer = new ExamFormerImpl(fileQuestionExtractor);
        List<Question> questionnaire = examFormer.getQuestionnaire();
        assertEquals(questionnaire, testQuestions);
    }

    @Test
    @DisplayName("test proper question return")
    void testGetQuestion() {
        Mockito.when(fileQuestionExtractor.getQuestionnaire()).thenReturn(testQuestions);
        ExamFormerImpl examFormer = new ExamFormerImpl(fileQuestionExtractor);
        Question question1 = examFormer.getQuestion(0);
        assertEquals(question1, testQuestions.get(0));
    }
}