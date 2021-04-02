package ca.purpose.edu;

import ca.purpose.edu.config.ServicesConfig;
import ca.purpose.edu.examiners.Examiner;
import ca.purpose.edu.models.Student;
import org.springframework.stereotype.Service;


@Service
public class ExamServiceImpl implements ExamService {
    private final Examiner examiner;
    private final ServicesConfig config;

    public ExamServiceImpl(Examiner examiner, ServicesConfig config) {
        this.examiner = examiner;
        this.config = config;
    }

    @Override
    public void runService(Student student) {
        student = examiner.runExam(student);
        String result = config.getLocaleMessage("result.msg", student.getFirstName(), student.getLastName(),
                String.valueOf(student.getMark()));
        System.out.println(result);
    }
}
