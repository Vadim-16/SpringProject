package ca.purpose.edu;

import ca.purpose.edu.Examiners.Examiner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ExamServiceImpl implements ExamService {
    private final Examiner examiner;

    public ExamServiceImpl(Examiner examiner) {
        this.examiner = examiner;
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(".\\spring-context.xml");
        ExamServiceImpl examService = context.getBean(ExamServiceImpl.class);
        examService.runService(new Student());
    }

    public void runService(Student student) {
        student = examiner.runExam(student);
        System.out.println("Thank you for your answers, " + student.getFirstName() + " " + student.getLastName()
                + "! Your mark: " + student.getMark());
        System.out.println(student);
    }
}
