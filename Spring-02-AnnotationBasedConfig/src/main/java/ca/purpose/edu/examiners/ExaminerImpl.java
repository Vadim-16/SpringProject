package ca.purpose.edu.examiners;

import ca.purpose.edu.models.Student;
import ca.purpose.edu.config.ServicesConfig;
import ca.purpose.edu.examformers.ExamFormer;
import ca.purpose.edu.models.Question;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ExaminerImpl implements Examiner {
    private final ExamFormer examFormer;

    public ExaminerImpl(ExamFormer examFormer) {
        this.examFormer = examFormer;
    }

    @Override
    public Student runExam(Student student) {
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            initStudent(student, consoleReader);
            evaluateStudent(student, consoleReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return student;
    }

    private void initStudent(Student student, BufferedReader consoleReader) throws IOException {
        MessageSource ms = ServicesConfig.messageSource("/message");
        String askFirstName = ms.getMessage("first.name", null, ServicesConfig.locale);
        String askLastName = ms.getMessage("last.name", null, ServicesConfig.locale);
        System.out.println(askFirstName);
        student.setFirstName(consoleReader.readLine());
        System.out.println(askLastName);
        student.setLastName(consoleReader.readLine());
    }

    private void evaluateStudent(Student student, BufferedReader consoleReader) throws IOException {
        int mark = 0;
        for (Question question : examFormer.getQuestionnaire()) {
            System.out.println(question.getQuestion());
            if (consoleReader.readLine().equalsIgnoreCase(question.getAnswer())) {
                mark++;
            }
        }
        student.setMark(mark);
    }
}
