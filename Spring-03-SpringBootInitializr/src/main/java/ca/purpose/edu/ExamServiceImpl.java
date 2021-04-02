package ca.purpose.edu;

import ca.purpose.edu.config.ServicesConfig;
import ca.purpose.edu.examiners.Examiner;
import ca.purpose.edu.models.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@SpringBootApplication
public class ExamServiceImpl implements ExamService {
    private final Examiner examiner;
    private final ServicesConfig config;

    public ExamServiceImpl(Examiner examiner, ServicesConfig config) {
        this.examiner = examiner;
        this.config = config;
    }

    public static void main(String[] args) {
        SpringApplication.run(ExamServiceImpl.class, args);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExamServiceImpl.class);
        ExamServiceImpl examService = context.getBean(ExamServiceImpl.class);
        examService.runService(new Student());
    }

    @Override
    public void runService(Student student) {
        student = examiner.runExam(student);
        String result = config.getLocaleMessage("result.msg", student.getFirstName(), student.getLastName(),
                String.valueOf(student.getMark()));
        System.out.println(result);
    }
}
