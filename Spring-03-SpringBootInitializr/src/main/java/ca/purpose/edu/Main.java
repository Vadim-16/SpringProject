package ca.purpose.edu;

import ca.purpose.edu.models.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        ExamServiceImpl examService = context.getBean(ExamServiceImpl.class);
        examService.runService(new Student());
    }
}
