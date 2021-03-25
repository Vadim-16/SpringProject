package ca.purpose.edu;

import ca.purpose.edu.Examiners.Examiner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ExamServiceImpl implements ExamService {
    private static String examPath;
    private final Examiner examiner;


    public ExamServiceImpl(String examPath, Examiner examiner) {
        this.examPath = examPath;
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
