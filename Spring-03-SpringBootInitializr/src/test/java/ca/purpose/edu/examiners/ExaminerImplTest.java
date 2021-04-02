package ca.purpose.edu.examiners;

import ca.purpose.edu.config.ServicesConfig;
import ca.purpose.edu.examformers.ExamFormer;
import ca.purpose.edu.models.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ExaminerImplTest {
    @MockBean
    private Examiner examiner;

    @MockBean
    private ExamFormer examFormer;

    @Autowired
    private ServicesConfig config;

    @Test
    void testRunExam() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Smith");
        student.setMark(5);
        Mockito.when(examiner.runExam(Mockito.any())).thenReturn(student);
        Student student1 = examiner.runExam(new Student());
        assertEquals(student, student1);
    }
}