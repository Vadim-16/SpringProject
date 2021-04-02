package ca.purpose.edu;

import ca.purpose.edu.config.ServicesConfig;
import ca.purpose.edu.examiners.Examiner;
import ca.purpose.edu.examiners.ExaminerImpl;
import ca.purpose.edu.models.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ExamServiceImplTest {
    @MockBean
    private Examiner examiner;

    @Autowired
    private ServicesConfig config;

    @Test
    void main() {
        ExamServiceImpl examService = new ExamServiceImpl(examiner, config);
    }
}